package com.dfavilav.marvelapplication.domain.repository

import androidx.paging.PagingData
import com.dfavilav.marvelapplication.domain.model.Comic
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getAllComics(): Flow<PagingData<Comic>>
}