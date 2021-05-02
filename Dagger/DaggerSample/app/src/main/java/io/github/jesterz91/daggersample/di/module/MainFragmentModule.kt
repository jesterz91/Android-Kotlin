package io.github.jesterz91.daggersample.di.module

import dagger.Module
import dagger.Provides
import io.github.jesterz91.daggersample.di.scope.FragmentScope
import kotlin.random.Random

@Module
object MainFragmentModule {

    @Provides
    @FragmentScope
    fun provideInt(): Int {
        return Random.nextInt()
    }
}