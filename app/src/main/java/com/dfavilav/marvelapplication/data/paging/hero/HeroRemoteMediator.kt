package com.dfavilav.marvelapplication.data.paging.hero

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.dfavilav.marvelapplication.data.local.MarvelDatabase
import com.dfavilav.marvelapplication.data.remote.MarvelApi
import com.dfavilav.marvelapplication.domain.model.hero.Hero
import com.dfavilav.marvelapplication.domain.model.hero.HeroRemoteKeys
import com.dfavilav.marvelapplication.util.Constants

@ExperimentalPagingApi
class HeroRemoteMediator(
    private val marvelApi: MarvelApi,
    private val database: MarvelDatabase
) : RemoteMediator<Int, Hero>() {

    private val heroDao = database.heroDao()
    private val heroRemoteKeysDao = database.heroRemoteKeysDao()

    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = heroRemoteKeysDao.getRemoteKeys(Constants.ONE)?.lastUpdated ?: Constants.ZERO_LONG

        val diffInMinutes = (currentTime - lastUpdated) / Constants.THOUSAND / Constants.MINUTE
        return if (diffInMinutes.toInt() <= Constants.CACHE_TIMEOUT) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Hero>
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

            val response = marvelApi.getAllHeroes()
            if (response.data.results.isNotEmpty()) {
                database.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        heroDao.deleteAllHeroes()
                        heroRemoteKeysDao.deleteAllRemoteKeys()
                    }
                    val prevOffset = response.data.offset
                    val nextOffset = response.data.offset?.plus(100)
                    val keys = response.data.results.map { hero ->
                        HeroRemoteKeys (
                            id = hero.id,
                            prevOffset = prevOffset,
                            nextOffset = nextOffset,
                            lastUpdated = System.currentTimeMillis()
                        )
                    }
                    heroRemoteKeysDao.addAllRemoteKeys(keys)
                    heroDao.addHeroes(response.data.results)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.data.total == null)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Hero>
    ): HeroRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                heroRemoteKeysDao.getRemoteKeys(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Hero>
    ): HeroRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { hero ->
                heroRemoteKeysDao.getRemoteKeys(hero.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Hero>
    ): HeroRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { comic ->
                heroRemoteKeysDao.getRemoteKeys(comic.id)
            }
    }
}