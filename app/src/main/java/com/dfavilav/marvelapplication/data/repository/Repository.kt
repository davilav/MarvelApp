package com.dfavilav.marvelapplication.data.repository

import androidx.paging.PagingData
import com.dfavilav.marvelapplication.domain.model.Comic
import com.dfavilav.marvelapplication.domain.repository.LocalDataSource
import com.dfavilav.marvelapplication.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource,
) {

    fun getAllComics(): Flow<PagingData<Comic>> {
        return remote.getAllComics()
    }

    suspend fun getSelectedComic(id: Int): Comic {
        return local.getSelectedComic(id)
    }
}