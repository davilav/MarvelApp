package com.dfavilav.marvelapplication.domain.model.comic

import kotlinx.serialization.Serializable


@Serializable
data class Data(
    var offset: Int? = null,
    var limit: Int? = null,
    var total: Int? = null,
    var count: Int? = null,
    var results: List<Comic> = listOf()
)
