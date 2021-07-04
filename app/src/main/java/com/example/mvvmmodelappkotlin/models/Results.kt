package com.example.mvvmmodelappkotlin.models

data class Results(
    var poster_path: String? = null,
    var title: String? = null,
    var vote_average: Double = 0.0,
    var popularity: Double = 0.0,
    var release_date: String? = null,
    var vote_count: Int = 0,
    var overview: String? = null,
    var original_title: String? = null,
    var original_language: String? = null,
    var id: Int = 0,
    var isVideo: Boolean = false,
    var isAdult: Boolean = false,
    var backdrop_path: String? = null
)