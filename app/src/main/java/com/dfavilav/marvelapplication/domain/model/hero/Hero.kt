package com.dfavilav.marvelapplication.domain.model.hero

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dfavilav.marvelapplication.domain.model.Thumbnail
import com.dfavilav.marvelapplication.util.Constants
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = Constants.CHARACTER_DATABASE_TABLE)
data class Hero(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var name: String,
    var description: String,
    var thumbnail: Thumbnail? = Thumbnail(),
)

