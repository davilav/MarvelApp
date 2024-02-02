package com.dfavilav.marvelapplication.di

import android.content.Context
import androidx.room.Room
import com.dfavilav.marvelapplication.data.local.MarvelDatabase
import com.dfavilav.marvelapplication.data.repository.LocalDataSourceImpl
import com.dfavilav.marvelapplication.domain.repository.LocalDataSource
import com.dfavilav.marvelapplication.util.Constants.MARVEL_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): MarvelDatabase {
        return Room.databaseBuilder(
            context,
            MarvelDatabase::class.java,
            MARVEL_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(
        database: MarvelDatabase
    ): LocalDataSource {
        return LocalDataSourceImpl(
            database
        )
    }

}