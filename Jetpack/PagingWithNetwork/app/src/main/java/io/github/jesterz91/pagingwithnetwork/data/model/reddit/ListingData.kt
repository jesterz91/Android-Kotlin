package io.github.jesterz91.pagingwithnetwork.data.model.reddit

import com.google.gson.annotations.SerializedName

data class ListingData(
    @SerializedName("children")
    val children: List<RedditChildrenResponse>,
    @SerializedName("after")
    val after: String?,
    @SerializedName("before")
    val before: String?
)