package com.dfavilav.marvelapplication.domain.use_cases

import com.dfavilav.marvelapplication.domain.use_cases.comic.GetAllComicsUseCase
import com.dfavilav.marvelapplication.domain.use_cases.comic.GetSelectedComicsUseCase
import com.dfavilav.marvelapplication.domain.use_cases.hero.GetAllHeroesUseCase
import com.dfavilav.marvelapplication.domain.use_cases.hero.GetSelectedHeroUseCase
import com.dfavilav.marvelapplication.domain.use_cases.hero.SearchHeroesUseCase

data class UseCases(
    val getAllHeroesUseCase: GetAllHeroesUseCase,
    val getSelectedHeroUseCase: GetSelectedHeroUseCase,
    val searchHeroesUseCase: SearchHeroesUseCase,
    val getAllComicsUseCase: GetAllComicsUseCase,
    val getSelectedComicsUseCase: GetSelectedComicsUseCase
)
