package io.github.jesterz91.daggersample.di.component

import dagger.Subcomponent
import dagger.android.AndroidInjector
import io.github.jesterz91.daggersample.di.module.MainActivityModule
import io.github.jesterz91.daggersample.di.scope.ActivityScope
import io.github.jesterz91.daggersample.ui.MainActivity

@ActivityScope
@Subcomponent(modules = [MainActivityModule::class])
interface MainActivityComponent: AndroidInjector<MainActivity> {

    @Subcomponent.Factory
    interface Factory: AndroidInjector.Factory<MainActivity>
}