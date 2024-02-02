package com.dfavilav.marvelapplication.domain.repository

import androidx.paging.PagingData
import com.dfavilav.marvelapplication.domain.model.comic.Comic
import com.dfavilav.marvelapplication.domain.model.hero.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getAllHeroes(): Flow<PagingData<Hero>>
    fun searchHeroes(query: String): Flow<PagingData<Hero>>

    fun getAllComics(): Flow<PagingData<Comic>>
}