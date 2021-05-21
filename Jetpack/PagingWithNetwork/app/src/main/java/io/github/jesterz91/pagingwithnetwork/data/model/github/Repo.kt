package io.github.jesterz91.pagingwithnetwork.data.model.github

import com.google.gson.annotations.SerializedName

data class Repo(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("url")
    val url: String,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    @SerializedName("forks")
    val forks: Int,
    @SerializedName("language")
    val language: String?
)