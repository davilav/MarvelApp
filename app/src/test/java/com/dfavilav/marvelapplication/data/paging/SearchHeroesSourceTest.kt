package com.dfavilav.marvelapplication.data.paging

import androidx.paging.PagingSource
import com.dfavilav.marvelapplication.data.local.MarvelDatabase
import com.dfavilav.marvelapplication.data.paging.hero.SearchHeroesSource
import com.dfavilav.marvelapplication.data.remote.FakeMarvelApi
import com.dfavilav.marvelapplication.data.remote.MarvelApi
import com.dfavilav.marvelapplication.domain.model.Thumbnail
import com.dfavilav.marvelapplication.domain.model.hero.Hero
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import kotlin.test.assertTrue

class SearchHeroesSourceTest {


    private lateinit var marvelApi: MarvelApi
    private lateinit var heroes: List<Hero>

    @Mock
    private lateinit var marvelDatabase: MarvelDatabase

    @Before
    fun setup() {
        marvelDatabase = mock(MarvelDatabase::class.java)
        marvelApi = FakeMarvelApi()
        heroes = listOf(
            Hero(
                id = 1,
                name = "Iron Man",
                description = "",
                thumbnail = Thumbnail("", "")
            ),
            Hero(
                id = 2,
                name = "Iron Man",
                description = "",
                thumbnail = Thumbnail("", "")
            ),
            Hero(
                id = 3,
                name = "Iron Man",
                description = "",
                thumbnail = Thumbnail("", "")
            )
        )
    }

    @Test
    fun `Search api with empty hero name, assert empty heroes list and LoadResult_Page`() =
        runTest {
            val charactersSource = SearchHeroesSource(
                marvelApi = marvelApi,
                query = "Iron Man",
                marvelDatabase
            )
            val loadResult = charactersSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )

            val result = marvelApi.searchHeroes("").data.results

            assertTrue { result.isEmpty() }
        }

    @Test
    fun `Search api with non_existing hero name, assert empty heroes list and LoadResult_Page`() =
        runTest {
            val charactersSource = SearchHeroesSource(
                marvelApi = marvelApi,
                query =  "Iron Man",
                marvelDatabase
            )
            val loadResult = charactersSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )

            val result = marvelApi.searchHeroes("Unknown").data.results

            assertTrue { result.isEmpty() }
        }
}




