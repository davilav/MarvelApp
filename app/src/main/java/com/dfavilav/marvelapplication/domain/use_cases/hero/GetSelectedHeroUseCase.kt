package com.dfavilav.marvelapplication.domain.use_cases.hero

import com.dfavilav.marvelapplication.data.repository.Repository
import com.dfavilav.marvelapplication.domain.model.hero.Hero

class GetSelectedHeroUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(id: Int): Hero {
        return repository.getSelectedHero(id)
    }
}