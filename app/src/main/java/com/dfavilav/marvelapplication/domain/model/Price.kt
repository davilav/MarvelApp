package com.dfavilav.marvelapplication.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Price(
    var type: String? = null,
    var price: Int? = null
)
