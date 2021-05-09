package io.github.jesterz91.daggersample.di.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import io.github.jesterz91.daggersample.di.component.MainFragmentComponent
import io.github.jesterz91.daggersample.di.scope.ActivityScope
import io.github.jesterz91.daggersample.ui.MainActivity
import io.github.jesterz91.daggersample.ui.MainFragment

@Module(subcomponents = [MainFragmentComponent::class])
abstract class MainActivityModule {

    @Binds
    @IntoMap
    @ClassKey(MainFragment::class)
    abstract fun bindAndroidInjectorFactory(factory: MainFragmentComponent.Factory): AndroidInjector.Factory<*>

    companion object {
        @Provides
        @ActivityScope
        fun provideActivityName(): String {
            return MainActivity::class.java.simpleName
        }
    }
}
