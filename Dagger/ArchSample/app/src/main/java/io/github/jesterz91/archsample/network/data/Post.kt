package io.github.jesterz91.archsample.network.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Post(
    @Json(name = "userId")
    val userId: Long,
    @Json(name = "id")
    val id: Long,
    @Json(name = "body")
    val body: String,
    @Json(name = "title")
    val title: String
) : Parcelable