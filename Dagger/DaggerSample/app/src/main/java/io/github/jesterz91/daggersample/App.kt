package io.github.jesterz91.daggersample

import android.app.Application
import io.github.jesterz91.daggersample.di.component.AppComponent
import io.github.jesterz91.daggersample.di.component.DaggerAppComponent
import io.github.jesterz91.daggersample.di.module.AppModule

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory()
            .newInstance(this, AppModule)
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}