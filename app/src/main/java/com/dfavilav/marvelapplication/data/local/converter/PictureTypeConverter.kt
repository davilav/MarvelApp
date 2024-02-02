package com.dfavilav.marvelapplication.data.local.converter

import androidx.room.TypeConverter
import com.dfavilav.marvelapplication.domain.model.Thumbnail
import com.dfavilav.marvelapplication.util.createUrlWithExtension
import com.dfavilav.marvelapplication.util.toThumbnail

class PictureTypeConverter {

    @TypeConverter
    fun thumbnailToString(thumbnail: Thumbnail): String? {
        return thumbnail.extension?.let { thumbnail.path?.createUrlWithExtension(it) }
    }

    @TypeConverter
    fun stringToThumbnail(value: String?): Thumbnail? {
        return value?.toThumbnail()
    }
}