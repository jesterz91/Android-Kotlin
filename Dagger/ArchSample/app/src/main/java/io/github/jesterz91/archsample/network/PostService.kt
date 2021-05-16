package io.github.jesterz91.archsample.network

import io.github.jesterz91.archsample.network.data.Post
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface PostService {

    @GET("/posts")
    fun getPosts(): Single<List<Post>>
}