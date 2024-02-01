package com.dfavilav.marvelapplication.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dfavilav.marvelapplication.domain.model.ComicRemoteKeys

@Dao
interface ComicRemoteKeysDao {

    @Query("SELECT * FROM comic_remote_keys_table WHERE id = :id")
    suspend fun getRemoteKeys(id: Int): ComicRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(characterRemoteKeys: List<ComicRemoteKeys?>)

    @Query("DELETE FROM comic_remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}