package com.dfavilav.marvelapplication.domain.use_cases

import com.dfavilav.marvelapplication.data.repository.Repository
import com.dfavilav.marvelapplication.domain.model.Comic

class GetSelectedComicsUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(id: Int): Comic {
        return repository.getSelectedComic(id)
    }
}