package io.github.jesterz91.daggersample.di.component

import dagger.BindsInstance
import dagger.Component
import io.github.jesterz91.daggersample.App
import io.github.jesterz91.daggersample.di.module.AppModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun mainActivityComponentBuilder(): MainActivityComponent.Builder

    fun inject(application: App)

    @Component.Factory
    interface Factory {

        fun newInstance(@BindsInstance application: App, appModule: AppModule): AppComponent
    }
}