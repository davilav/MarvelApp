package com.dfavilav.marvelapplication.domain.use_cases.hero

import androidx.paging.PagingData
import com.dfavilav.marvelapplication.data.repository.Repository
import com.dfavilav.marvelapplication.domain.model.hero.Hero
import kotlinx.coroutines.flow.Flow

class SearchHeroesUseCase(
    private val repository: Repository
) {

    operator fun invoke(query: String): Flow<PagingData<Hero>> {
        return repository.searchHeroes(query)
    }
}