package com.dfavilav.marvelapplication.domain.model

data class Result(
    var id: Int? = null,
    var digitalId: Int? = null,
    var title: String? = null,
    var variantDescription: String? = null,
    var description: String? = null,
    var modified: String? = null,
    var prices: ArrayList<Price> = arrayListOf(),
    var thumbnail: Thumbnail? = Thumbnail(),
    var images: ArrayList<String> = arrayListOf(),
)
