package com.dfavilav.marvelapplication.domain.model

data class Data(
    var offset: Int? = null,
    var limit: Int? = null,
    var total: Int? = null,
    var count: Int? = null,
    var results: List<Result> = listOf()
)
