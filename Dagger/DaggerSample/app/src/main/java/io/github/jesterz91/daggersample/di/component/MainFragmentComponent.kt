package io.github.jesterz91.daggersample.di.component

import dagger.BindsInstance
import dagger.Subcomponent
import io.github.jesterz91.daggersample.di.module.MainFragmentModule
import io.github.jesterz91.daggersample.di.scope.FragmentScope
import io.github.jesterz91.daggersample.ui.MainFragment

@FragmentScope
@Subcomponent(modules = [MainFragmentModule::class])
interface MainFragmentComponent {

    fun inject(mainFragment: MainFragment)

    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        fun setFragment(mainFragment: MainFragment): Builder

        fun setModule(module: MainFragmentModule): Builder

        fun build(): MainFragmentComponent
    }
}