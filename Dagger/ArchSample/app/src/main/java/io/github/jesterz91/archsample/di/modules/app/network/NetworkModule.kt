package io.github.jesterz91.archsample.di.modules.app.network

import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.github.jesterz91.archsample.BuildConfig
import io.github.jesterz91.archsample.network.CommentService
import io.github.jesterz91.archsample.network.PostService
import io.github.jesterz91.archsample.network.UserService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    @Reusable
    @Provides
    fun providePostService(retrofit: Retrofit): PostService {
        return retrofit.create(PostService::class.java)
    }

    @Reusable
    @Provides
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Reusable
    @Provides
    fun provideCommentService(retrofit: Retrofit): CommentService {
        return retrofit.create(CommentService::class.java)
    }

    @Provides
    @Singleton
    fun provideApi(
        client: OkHttpClient,
        callAdapter: CallAdapter.Factory,
        converter: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(client)
            .addCallAdapterFactory(callAdapter)
            .addConverterFactory(converter)
            .build()
    }

    @Provides
    @Singleton
    fun provideClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    @Singleton
    fun provideCallAdapter(): CallAdapter.Factory {
        return RxJava3CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun provideConverter(): Converter.Factory {
        return MoshiConverterFactory.create()
    }
}