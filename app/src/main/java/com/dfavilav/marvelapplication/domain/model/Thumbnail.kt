package com.dfavilav.marvelapplication.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Thumbnail(
    var path: String? = null,
    var extension: String? = null
)
