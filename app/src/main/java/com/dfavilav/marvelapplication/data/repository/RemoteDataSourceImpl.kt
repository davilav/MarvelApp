package com.dfavilav.marvelapplication.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dfavilav.marvelapplication.data.local.MarvelDatabase
import com.dfavilav.marvelapplication.data.paging.ComicRemoteMediator
import com.dfavilav.marvelapplication.data.remote.MarvelApi
import com.dfavilav.marvelapplication.domain.model.Comic
import com.dfavilav.marvelapplication.domain.repository.RemoteDataSource
import com.dfavilav.marvelapplication.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val marvelApi: MarvelApi,
    private val marvelDatabase: MarvelDatabase
) : RemoteDataSource {

    private val comicsDao = marvelDatabase.comicDao()


    override fun getAllComics(): Flow<PagingData<Comic>> {
        val pagingSourceFactory = { comicsDao.getAllComics() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = ComicRemoteMediator(
                marvelApi,
                marvelDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}
