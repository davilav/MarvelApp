package com.dfavilav.marvelapplication.domain.use_cases.comic

import androidx.paging.PagingData
import com.dfavilav.marvelapplication.data.repository.Repository
import com.dfavilav.marvelapplication.domain.model.comic.Comic
import kotlinx.coroutines.flow.Flow

class GetAllComicsUseCase(
    private val repository: Repository,
) {
    operator fun invoke(id: Int): Flow<PagingData<Comic>> {
        return repository.getAllComics(id)
    }
}