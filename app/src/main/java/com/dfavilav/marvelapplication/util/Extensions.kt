package com.dfavilav.marvelapplication.util

import com.dfavilav.marvelapplication.domain.model.Thumbnail

fun String.createUrlWithExtension(extension: String): String {
    return "$this.$extension"
}

fun String.toThumbnail(): Thumbnail? {
    val lastDotIndex = lastIndexOf('.')
    if (lastDotIndex != -1 && lastDotIndex < length - 1) {
        val path = substring(0, lastDotIndex)
        val extension = substring(lastDotIndex + 1)
        return Thumbnail(path, extension)
    }
    return null
}
