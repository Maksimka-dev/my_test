package com.example.thecatapi.data

import com.squareup.moshi.Json

data class ApiData(
    @Json(name = "height") val height: Int,
    @Json(name = "id") val id: String,
    @Json(name = "url") val url: String,
    @Json(name = "width") val width: Int
)