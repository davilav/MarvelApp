package com.dfavilav.marvelapplication.domain.repository

import com.dfavilav.marvelapplication.domain.model.Comic

interface LocalDataSource {

    suspend fun getSelectedComic(id: Int): Comic
}