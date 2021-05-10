package io.github.jesterz91.daggersample

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.github.jesterz91.daggersample.di.component.DaggerAppComponent

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory()
                .create(this)
    }
}