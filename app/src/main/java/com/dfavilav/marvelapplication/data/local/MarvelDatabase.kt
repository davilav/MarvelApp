package com.dfavilav.marvelapplication.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dfavilav.marvelapplication.data.local.dao.ComicRemoteKeysDao
import com.dfavilav.marvelapplication.data.local.dao.ComicsDao

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
}