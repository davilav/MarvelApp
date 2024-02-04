package com.dfavilav.marvelapplication.data.paging.comic

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.dfavilav.marvelapplication.data.local.MarvelDatabase
import com.dfavilav.marvelapplication.data.remote.MarvelApi
import com.dfavilav.marvelapplication.domain.model.comic.Comic
import com.dfavilav.marvelapplication.domain.model.comic.ComicRemoteKeys
import com.dfavilav.marvelapplication.util.Constants.CACHE_TIMEOUT
import com.dfavilav.marvelapplication.util.Constants.MINUTE
import com.dfavilav.marvelapplication.util.Constants.ONE
import com.dfavilav.marvelapplication.util.Constants.THOUSAND
import com.dfavilav.marvelapplication.util.Constants.ZERO_LONG

@ExperimentalPagingApi
class ComicRemoteMediator(
    private val marvelApi: MarvelApi,
    private val database: MarvelDatabase,
    private val characterId: Int
) : RemoteMediator<Int, Comic>() {

    private val comicDao = database.comicDao()
    private val comicRemoteKeysDao = database.comicRemoteKeysDao()

    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = comicRemoteKeysDao.getRemoteKeys(ONE)?.lastUpdated ?: ZERO_LONG

        val diffInMinutes = (currentTime - lastUpdated) / THOUSAND / MINUTE
        return if (diffInMinutes.toInt() <= CACHE_TIMEOUT) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Comic>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextOffset?.minus(100) ?: 100
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevOffset
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextOffset
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = marvelApi.getAllComicsByCharacter(characterId)
            if (response.data.results.isNotEmpty()) {
                database.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        comicDao.deleteAllComics()
                        comicRemoteKeysDao.deleteAllRemoteKeys()
                    }
                    val prevOffset = response.data.offset
                    val nextOffset = response.data.offset?.plus(100)
                    val keys = response.data.results.map { comic ->
                        ComicRemoteKeys(
                                id = comic.id,
                                prevOffset = prevOffset,
                                nextOffset = nextOffset,
                                lastUpdated = System.currentTimeMillis()
                            )
                    }
                    comicRemoteKeysDao.addAllRemoteKeys(keys)
                    comicDao.addComics(response.data.results)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.data.total == null)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Comic>
    ): ComicRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                comicRemoteKeysDao.getRemoteKeys(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Comic>
    ): ComicRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { comic ->
                comicRemoteKeysDao.getRemoteKeys(comic.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Comic>
    ): ComicRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { comic ->
                comicRemoteKeysDao.getRemoteKeys(comic.id)
            }
    }
}