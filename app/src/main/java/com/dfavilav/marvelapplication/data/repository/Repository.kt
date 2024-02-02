package com.dfavilav.marvelapplication.data.repository

import androidx.paging.PagingData
import com.dfavilav.marvelapplication.domain.model.comic.Comic
import com.dfavilav.marvelapplication.domain.model.hero.Hero
import com.dfavilav.marvelapplication.domain.repository.LocalDataSource
import com.dfavilav.marvelapplication.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource,
) {

    fun getAllHeroes(): Flow<PagingData<Hero>> {
        return remote.getAllHeroes()
    }

    suspend fun getSelectedHero(id: Int): Hero {
        return local.getSelectedHero(id)
    }

    fun searchHeroes(query: String): Flow<PagingData<Hero>> {
        return remote.searchHeroes(query)
    }

    fun getAllComics(): Flow<PagingData<Comic>> {
        return remote.getAllComics()
    }

    suspend fun getSelectedComic(id: Int): Comic {
        return local.getSelectedComic(id)
    }
}