package io.github.jesterz91.daggersample.di.module

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import io.github.jesterz91.daggersample.App
import io.github.jesterz91.daggersample.di.component.MainActivityComponent
import io.github.jesterz91.daggersample.ui.MainActivity
import javax.inject.Singleton

@Module(subcomponents = [MainActivityComponent::class])
abstract class AppModule {

    @Binds
    @IntoMap
    @ClassKey(MainActivity::class)
    abstract fun bindAndroidInjectorFactory(factory: MainActivityComponent.Factory): AndroidInjector.Factory<*>

    companion object {
        @Provides
        @Singleton
        fun provideSharedPreferences(application: App): SharedPreferences {
            return PreferenceManager.getDefaultSharedPreferences(application)
        }
    }
}