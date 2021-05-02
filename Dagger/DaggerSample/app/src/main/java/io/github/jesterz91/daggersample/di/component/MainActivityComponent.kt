package io.github.jesterz91.daggersample.di.component

import dagger.BindsInstance
import dagger.Subcomponent
import io.github.jesterz91.daggersample.di.module.MainActivityModule
import io.github.jesterz91.daggersample.di.scope.ActivityScope
import io.github.jesterz91.daggersample.ui.MainActivity

@ActivityScope
@Subcomponent(modules = [MainActivityModule::class])
interface MainActivityComponent {

    fun mainFragmentBuilder(): MainFragmentComponent.Builder

    fun inject(mainActivity: MainActivity)

    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        fun setActivity(mainActivity: MainActivity): Builder

        fun setModule(module: MainActivityModule): Builder

        fun build(): MainActivityComponent
    }
}
