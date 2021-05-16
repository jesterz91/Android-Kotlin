package io.github.jesterz91.archsample

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.github.jesterz91.archsample.di.component.DaggerAppComponent
import io.github.jesterz91.archsample.util.Logger

class App : DaggerApplication(), Logger {

    override fun onCreate() {
        super.onCreate()
        debug("Application Created")
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}