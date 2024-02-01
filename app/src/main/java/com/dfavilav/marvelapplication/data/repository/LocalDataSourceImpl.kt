package com.dfavilav.marvelapplication.data.repository

import com.dfavilav.marvelapplication.data.local.MarvelDatabase
import com.dfavilav.marvelapplication.domain.model.Comic
import com.dfavilav.marvelapplication.domain.repository.LocalDataSource

class LocalDataSourceImpl(marvelDatabase: MarvelDatabase) : LocalDataSource {
    private val comicDao = marvelDatabase.comicDao()
    override suspend fun getSelectedComic(id: Int): Comic {
        return comicDao.getSelectedComics(id)
    }
}