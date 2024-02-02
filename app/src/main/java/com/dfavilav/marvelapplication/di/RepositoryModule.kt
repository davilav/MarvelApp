package com.dfavilav.marvelapplication.di

import com.dfavilav.marvelapplication.data.repository.Repository
import com.dfavilav.marvelapplication.domain.use_cases.comic.GetAllComicsUseCase
import com.dfavilav.marvelapplication.domain.use_cases.comic.GetSelectedComicsUseCase
import com.dfavilav.marvelapplication.domain.use_cases.UseCases
import com.dfavilav.marvelapplication.domain.use_cases.hero.GetAllHeroesUseCase
import com.dfavilav.marvelapplication.domain.use_cases.hero.GetSelectedHeroUseCase
import com.dfavilav.marvelapplication.domain.use_cases.hero.SearchHeroesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            GetAllHeroesUseCase(repository),
            GetSelectedHeroUseCase(repository),
            SearchHeroesUseCase(repository),
            GetAllComicsUseCase(repository),
            GetSelectedComicsUseCase(repository)
        )
    }
}