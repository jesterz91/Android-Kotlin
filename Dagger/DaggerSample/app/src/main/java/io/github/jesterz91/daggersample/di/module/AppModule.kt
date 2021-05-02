package io.github.jesterz91.daggersample.di.module

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import io.github.jesterz91.daggersample.App
import io.github.jesterz91.daggersample.di.component.MainActivityComponent
import javax.inject.Singleton

@Module(subcomponents = [MainActivityComponent::class])
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(application: App): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }
}