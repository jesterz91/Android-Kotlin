package io.github.jesterz91.archsample.di.modules.activity

import android.content.Context
import androidx.databinding.DataBindingUtil
import dagger.Module
import dagger.Provides
import io.github.jesterz91.archsample.R
import io.github.jesterz91.archsample.databinding.ActivityMainBinding
import io.github.jesterz91.archsample.di.qualifier.ActivityContext
import io.github.jesterz91.archsample.di.scope.ActivityScope
import io.github.jesterz91.archsample.ui.MainActivity

@Module
object MainModule {

    @Provides
    @ActivityScope
    fun provideBinding(activity: MainActivity): ActivityMainBinding {
        return DataBindingUtil.setContentView(activity, R.layout.activity_main)
    }

    @Provides
    @ActivityContext
    fun provideContext(activity: MainActivity): Context {
        return activity
    }
}