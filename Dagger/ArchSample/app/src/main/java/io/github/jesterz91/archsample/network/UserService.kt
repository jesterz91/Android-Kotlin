package io.github.jesterz91.archsample.network

import io.github.jesterz91.archsample.network.data.User
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {

    @GET("/users/{userId}")
    fun getUser(@Path("userId") id: Long): Single<User>
}