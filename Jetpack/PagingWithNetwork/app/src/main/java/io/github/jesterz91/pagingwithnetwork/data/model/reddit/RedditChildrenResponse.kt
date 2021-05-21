package io.github.jesterz91.pagingwithnetwork.data.model.reddit

import com.google.gson.annotations.SerializedName

data class RedditChildrenResponse(
    @SerializedName("data")
    val data: RedditPost
)