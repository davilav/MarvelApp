package com.dfavilav.marvelapplication.domain.model.comic

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val code: Int? = null,
    val data: Data = Data(),
    )