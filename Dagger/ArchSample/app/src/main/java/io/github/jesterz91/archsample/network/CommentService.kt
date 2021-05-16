package io.github.jesterz91.archsample.network

import io.github.jesterz91.archsample.network.data.Comment
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentService {

    @GET("/comments")
    fun getComments(@Query("postId") id: Long): Single<List<Comment>>
}