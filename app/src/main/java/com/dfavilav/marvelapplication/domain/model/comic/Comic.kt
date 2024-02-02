package com.dfavilav.marvelapplication.domain.model.comic

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dfavilav.marvelapplication.domain.model.Price
import com.dfavilav.marvelapplication.domain.model.Thumbnail
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
    var thumbnail: Thumbnail? = Thumbnail(),
)
