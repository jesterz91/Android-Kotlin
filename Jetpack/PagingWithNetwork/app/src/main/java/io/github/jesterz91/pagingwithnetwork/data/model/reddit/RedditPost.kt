package io.github.jesterz91.pagingwithnetwork.data.model.reddit

import com.google.gson.annotations.SerializedName

data class RedditPost(
    @SerializedName("name")
    val name: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("score")
    val score: Int,
    @SerializedName("author")
    val author: String,
    @SerializedName("subreddit")
    val subreddit: String,
    @SerializedName("num_comments")
    val numComments: Int,
    @SerializedName("created_utc")
    val created_utc: Long,
    @SerializedName("thumbnail")
    val thumbnail: String?,
    @SerializedName("url")
    val url: String?
)