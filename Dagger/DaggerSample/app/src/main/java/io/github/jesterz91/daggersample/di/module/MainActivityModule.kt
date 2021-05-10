package io.github.jesterz91.daggersample.di.module

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import io.github.jesterz91.daggersample.di.component.MainFragmentComponent
import io.github.jesterz91.daggersample.di.scope.ActivityScope
import io.github.jesterz91.daggersample.di.scope.FragmentScope
import io.github.jesterz91.daggersample.ui.MainActivity
import io.github.jesterz91.daggersample.ui.MainFragment

@Module(subcomponents = [MainFragmentComponent::class])
abstract class MainActivityModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    abstract fun mainFragment(): MainFragment

    companion object {
        @Provides
        @ActivityScope
        fun provideActivityName(): String {
            return MainActivity::class.java.simpleName
        }
    }
}
