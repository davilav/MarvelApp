package com.dfavilav.marvelapplication.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dfavilav.marvelapplication.util.Constants.COMIC_REMOTE_KEYS_DATABASE_TABLE

@Entity(tableName = COMIC_REMOTE_KEYS_DATABASE_TABLE)
data class ComicRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevOffset: Int?,
    val nextOffset: Int?,
    val lastUpdated: Long?
)
