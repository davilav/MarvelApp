package com.dfavilav.marvelapplication.data.repository

import com.dfavilav.marvelapplication.data.local.MarvelDatabase
import com.dfavilav.marvelapplication.domain.model.comic.Comic
import com.dfavilav.marvelapplication.domain.model.hero.Hero
import com.dfavilav.marvelapplication.domain.repository.LocalDataSource

class LocalDataSourceImpl(marvelDatabase: MarvelDatabase) : LocalDataSource {

    private val comicDao = marvelDatabase.comicDao()
    private val heroDao = marvelDatabase.heroDao()
    override suspend fun getSelectedHero(id: Int): Hero {
        return heroDao.getSelectedHeroes(id)
    }

    override suspend fun getSelectedComic(id: Int): Comic {
        return comicDao.getSelectedComics(id)
    }
}
