package io.github.jesterz91.archsample.di.modules.app

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.jesterz91.archsample.di.modules.activity.MainModule
import io.github.jesterz91.archsample.di.scope.ActivityScope
import io.github.jesterz91.archsample.ui.MainActivity

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun bindMainActivity(): MainActivity
}