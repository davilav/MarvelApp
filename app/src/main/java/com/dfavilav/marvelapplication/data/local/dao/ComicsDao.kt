package com.dfavilav.marvelapplication.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dfavilav.marvelapplication.domain.model.Comic

@Dao
interface ComicsDao {

    @Query("SELECT * FROM comic_table ORDER BY id ASC")
    fun getAllComics(): PagingSource<Int, Comic>

    @Query("SELECT * FROM comic_table WHERE id=:id")
    fun getSelectedComics(id: Int): Comic

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addComics(characters: List<Comic>)

    @Query("DELETE FROM comic_table")
    suspend fun deleteAllComics()
}
