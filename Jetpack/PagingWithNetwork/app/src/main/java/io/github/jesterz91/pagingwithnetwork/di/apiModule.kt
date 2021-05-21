package io.github.jesterz91.pagingwithnetwork.di

import io.github.jesterz91.pagingwithnetwork.BuildConfig
import io.github.jesterz91.pagingwithnetwork.data.GithubService
import io.github.jesterz91.pagingwithnetwork.data.RedditService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {

    single {
        get<Retrofit.Builder>()
            .baseUrl(GithubService.BASE_URL)
            .build()
            .create(GithubService::class.java)
    }

    single {
        get<Retrofit.Builder>()
            .baseUrl(RedditService.BASE_URL)
            .build()
            .create(RedditService::class.java)
    }

    single {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(get<GsonConverterFactory>())
            .addCallAdapterFactory(get<RxJava3CallAdapterFactory>())
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get() as HttpLoggingInterceptor).build()
    }

    single {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.BASIC
            }
        }
    }

    single { GsonConverterFactory.create() }

    single { RxJava3CallAdapterFactory.create() }
}