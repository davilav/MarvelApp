package com.dfavilav.marvelapplication.domain.model.hero

import kotlinx.serialization.Serializable

@Serializable
data class HeroData(
    var offset: Int? = null,
    var limit: Int? = null,
    var total: Int? = null,
    var count: Int? = null,
    var results: List<Hero> = listOf()
)
