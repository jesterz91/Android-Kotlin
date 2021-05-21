package io.github.jesterz91.pagingwithnetwork.data.model.github

import com.google.gson.annotations.SerializedName

data class RepoSearchResponse(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("incomplete_results")
    val incompleteResults: String,
    @SerializedName("items")
    val items: List<Repo>
)