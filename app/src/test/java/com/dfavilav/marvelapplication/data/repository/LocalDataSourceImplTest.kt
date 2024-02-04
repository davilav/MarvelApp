package com.dfavilav.marvelapplication.data.repository

import com.dfavilav.marvelapplication.data.local.MarvelDatabase
import com.dfavilav.marvelapplication.data.local.dao.character.HeroDao
import com.dfavilav.marvelapplication.data.local.dao.comic.ComicsDao
import com.dfavilav.marvelapplication.domain.model.Thumbnail
import com.dfavilav.marvelapplication.domain.model.comic.Comic
import com.dfavilav.marvelapplication.domain.model.hero.Hero
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class LocalDataSourceImplTest {

    @Mock
    lateinit var marvelDatabase: MarvelDatabase

    @Mock
    lateinit var heroDao: HeroDao

    @Mock
    lateinit var comicDao: ComicsDao

    private lateinit var localDataSource: LocalDataSourceImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        `when`(marvelDatabase.heroDao()).thenReturn(heroDao)
        `when`(marvelDatabase.comicDao()).thenReturn(comicDao)
        localDataSource = LocalDataSourceImpl(marvelDatabase)
    }

    @Test
    fun getSelectedCharacter_shouldReturnCharacter() {
        val characterId = 1
        val mockCharacter = Hero(
            id = 1,
            name = "Iron Man",
            description = "",
            thumbnail = Thumbnail("", ""),
        )

        `when`(heroDao.getSelectedHeroes(characterId)).thenReturn(mockCharacter)
        runBlocking {
            val resultCharacter = localDataSource.getSelectedHero(characterId)
            verify(heroDao).getSelectedHeroes(characterId)
            assertEquals(mockCharacter, resultCharacter)
        }
    }

    @Test
    fun getSelectedComic_shouldReturnComic() {
        val comicId = 1
        val mockComic = Comic(
            id = 1,
            title = "Iron Man",
            description = "",
            thumbnail = Thumbnail("", ""),
        )

        `when`(comicDao.getSelectedComics(comicId)).thenReturn(mockComic)
        runBlocking {
            val resultCharacter = localDataSource.getSelectedComic(comicId)
            verify(comicDao).getSelectedComics(comicId)
            assertEquals(mockComic, resultCharacter)
        }
    }
}