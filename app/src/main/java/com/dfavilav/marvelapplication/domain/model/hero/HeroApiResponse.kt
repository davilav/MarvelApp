package com.dfavilav.marvelapplication.domain.model.hero

import kotlinx.serialization.Serializable

@Serializable
data class HeroApiResponse(
    val code: Int? = null,
    val data: HeroData = HeroData(),
)
