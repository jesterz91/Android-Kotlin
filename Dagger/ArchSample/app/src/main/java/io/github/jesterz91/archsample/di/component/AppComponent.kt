package io.github.jesterz91.archsample.di.component

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.github.jesterz91.archsample.App
import io.github.jesterz91.archsample.di.modules.app.ActivityBindingModule
import io.github.jesterz91.archsample.di.modules.app.AppModule
import io.github.jesterz91.archsample.di.modules.app.FragmentBindingModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBindingModule::class,
    FragmentBindingModule::class,
    AppModule::class,
])
interface AppComponent: AndroidInjector<App> {

    @Component.Factory
    interface Factory: AndroidInjector.Factory<App>
}