package io.github.jesterz91.pagingwithnetwork.data

import io.github.jesterz91.pagingwithnetwork.data.model.reddit.ListingResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RedditService {

    @GET("/r/{subreddit}/hot.json")
    suspend fun requestSubreddit(
        @Path("subreddit") subreddit: String,
        @Query("limit") limit: Int,
        @Query("after") after: String?
    ): ListingResponse

    companion object {
        const val BASE_URL = "https://www.reddit.com/"
    }
}