package io.github.jesterz91.pagingwithnetwork.data

import io.github.jesterz91.pagingwithnetwork.data.model.github.RepoSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {

    @GET("search/repositories?sort=stars")
    suspend fun searchRepos(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): RepoSearchResponse

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }
}