package io.github.jesterz91.daggersample.di.module

import dagger.Module
import dagger.Provides
import io.github.jesterz91.daggersample.di.component.MainFragmentComponent
import io.github.jesterz91.daggersample.di.scope.ActivityScope
import io.github.jesterz91.daggersample.ui.MainActivity

@Module(subcomponents = [MainFragmentComponent::class])
object MainActivityModule {

    @Provides
    @ActivityScope
    fun provideActivityName(): String {
        return MainActivity::class.java.simpleName
    }
}
