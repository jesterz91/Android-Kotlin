package io.github.jesterz91.daggersample.di.module

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import io.github.jesterz91.daggersample.App
import io.github.jesterz91.daggersample.di.component.MainActivityComponent
import io.github.jesterz91.daggersample.di.scope.ActivityScope
import io.github.jesterz91.daggersample.ui.MainActivity
import javax.inject.Singleton

@Module(subcomponents = [MainActivityComponent::class])
abstract class AppModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivity(): MainActivity

    companion object {
        @Provides
        @Singleton
        fun provideSharedPreferences(application: App): SharedPreferences {
            return PreferenceManager.getDefaultSharedPreferences(application)
        }
    }
}