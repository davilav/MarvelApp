package com.dfavilav.marvelapplication.domain.model.hero

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dfavilav.marvelapplication.util.Constants

@Entity(tableName = Constants.CHARACTER_REMOTE_KEYS_DATABASE_TABLE)
data class HeroRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevOffset: Int?,
    val nextOffset: Int?,
    val lastUpdated: Long?
)
