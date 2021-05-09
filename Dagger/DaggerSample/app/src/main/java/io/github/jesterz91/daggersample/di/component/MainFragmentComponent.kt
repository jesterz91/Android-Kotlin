package io.github.jesterz91.daggersample.di.component

import dagger.Subcomponent
import dagger.android.AndroidInjector
import io.github.jesterz91.daggersample.di.module.MainFragmentModule
import io.github.jesterz91.daggersample.di.scope.FragmentScope
import io.github.jesterz91.daggersample.ui.MainFragment

@FragmentScope
@Subcomponent(modules = [MainFragmentModule::class])
interface MainFragmentComponent: AndroidInjector<MainFragment> {

    @Subcomponent.Factory
    interface Factory: AndroidInjector.Factory<MainFragment>
}