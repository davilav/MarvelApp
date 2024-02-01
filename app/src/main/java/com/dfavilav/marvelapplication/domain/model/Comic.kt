package com.dfavilav.marvelapplication.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dfavilav.marvelapplication.util.Constants
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = Constants.COMIC_DATABASE_TABLE)
data class Comic(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var digitalId: Int? = null,
    var title: String? = null,
    var variantDescription: String? = null,
    var description: String? = null,
    var modified: String? = null,
    var prices: ArrayList<Price> = arrayListOf(),
    var thumbnail: Thumbnail? = Thumbnail(),
    var images: ArrayList<String> = arrayListOf(),
)
