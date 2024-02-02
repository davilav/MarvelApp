package com.dfavilav.marvelapplication.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dfavilav.marvelapplication.data.local.converter.PictureTypeConverter
import com.dfavilav.marvelapplication.data.local.dao.character.HeroDao
import com.dfavilav.marvelapplication.data.local.dao.character.HeroRemoteKeysDao
import com.dfavilav.marvelapplication.data.local.dao.comic.ComicRemoteKeysDao
import com.dfavilav.marvelapplication.data.local.dao.comic.ComicsDao
import com.dfavilav.marvelapplication.domain.model.comic.Comic
import com.dfavilav.marvelapplication.domain.model.comic.ComicRemoteKeys
import com.dfavilav.marvelapplication.domain.model.hero.Hero
import com.dfavilav.marvelapplication.domain.model.hero.HeroRemoteKeys

@Database(entities = [Hero::class, HeroRemoteKeys::class, Comic::class, ComicRemoteKeys::class], version = 1)
@TypeConverters(PictureTypeConverter::class)
abstract class MarvelDatabase : RoomDatabase() {

    companion object {
        fun create(context: Context, useInMemory: Boolean): MarvelDatabase {
            val databaseBuilder = if (useInMemory) {
                Room.inMemoryDatabaseBuilder(context, MarvelDatabase::class.java)
            } else {
                Room.databaseBuilder(context, MarvelDatabase::class.java, "test_database.db")
            }
            return databaseBuilder
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun comicDao(): ComicsDao
    abstract fun comicRemoteKeysDao(): ComicRemoteKeysDao
    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeysDao(): HeroRemoteKeysDao
}