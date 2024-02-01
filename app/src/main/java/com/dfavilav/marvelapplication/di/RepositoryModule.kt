package com.dfavilav.marvelapplication.di

import com.dfavilav.marvelapplication.data.repository.Repository
import com.dfavilav.marvelapplication.domain.use_cases.GetAllComicsUseCase
import com.dfavilav.marvelapplication.domain.use_cases.GetSelectedComicsUseCase
import com.dfavilav.marvelapplication.domain.use_cases.UseCases
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
            GetAllComicsUseCase(repository),
            GetSelectedComicsUseCase(repository)
        )
    }
}