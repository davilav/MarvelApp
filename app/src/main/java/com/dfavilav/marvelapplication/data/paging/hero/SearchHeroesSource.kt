package com.dfavilav.marvelapplication.data.paging.hero

import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.room.withTransaction
import com.dfavilav.marvelapplication.data.local.MarvelDatabase
import com.dfavilav.marvelapplication.data.remote.MarvelApi
import com.dfavilav.marvelapplication.domain.model.hero.Hero
import java.lang.Exception

class SearchHeroesSource(
    private val marvelApi: MarvelApi,
    private val query: String,
    private val marvelDatabase: MarvelDatabase
) : PagingSource<Int, Hero>() {

    private val characterDao = marvelDatabase.heroDao()

    override fun getRefreshKey(state: PagingState<Int, Hero>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hero> {
        return try {
            val apiResponse = marvelApi.searchHeroes(query)
            val characters = apiResponse.data.results
            marvelDatabase.withTransaction { characterDao.addHeroes(characters) }
            LoadResult.Page(
                data = characters.ifEmpty { emptyList() },
                prevKey = null,
                nextKey = null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}