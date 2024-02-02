package com.dfavilav.marvelapplication.data.local.dao.character

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dfavilav.marvelapplication.domain.model.hero.Hero

@Dao
interface HeroDao {

    @Query("SELECT * FROM character_table ORDER BY id ASC")
    fun getAllHeroes(): PagingSource<Int, Hero>

    @Query("SELECT * FROM character_table WHERE id=:id")
    fun getSelectedHeroes(id: Int): Hero

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHeroes(heroes: List<Hero>)

    @Query("DELETE FROM character_table")
    suspend fun deleteAllHeroes()
}