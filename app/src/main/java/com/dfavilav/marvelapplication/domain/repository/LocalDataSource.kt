package com.dfavilav.marvelapplication.domain.repository

import com.dfavilav.marvelapplication.domain.model.comic.Comic
import com.dfavilav.marvelapplication.domain.model.hero.Hero

interface LocalDataSource {

    suspend fun getSelectedHero(id: Int): Hero
    suspend fun getSelectedComic(id: Int): Comic

}