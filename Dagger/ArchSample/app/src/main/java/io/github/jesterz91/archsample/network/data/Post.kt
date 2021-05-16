package io.github.jesterz91.archsample.network.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Post(
    @Json(name = "userId")
    val userId: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "body")
    val body: String,
    @Json(name = "title")
    val title: String
)