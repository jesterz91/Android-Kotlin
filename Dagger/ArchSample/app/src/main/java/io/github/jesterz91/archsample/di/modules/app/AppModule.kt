package io.github.jesterz91.archsample.di.modules.app

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.github.jesterz91.archsample.App
import io.github.jesterz91.archsample.di.modules.app.network.NetworkModule
import io.github.jesterz91.archsample.di.modules.app.viewmodel.ViewModelModule
import io.github.jesterz91.archsample.di.qualifier.ApplicationContext
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, NetworkModule::class])
object AppModule {

    @Provides
    @Singleton
    fun provideApp(app: App): Application = app

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(app: App): Context = app
}