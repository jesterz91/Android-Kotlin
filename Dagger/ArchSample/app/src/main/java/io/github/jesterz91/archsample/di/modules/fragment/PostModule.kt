package io.github.jesterz91.archsample.di.modules.fragment

import android.content.Context
import android.view.LayoutInflater
import dagger.Module
import dagger.Provides
import io.github.jesterz91.archsample.databinding.FragmentPostBinding
import io.github.jesterz91.archsample.di.qualifier.ApplicationContext
import io.github.jesterz91.archsample.di.scope.FragmentScope

@Module
object PostModule {

    @Provides
    @FragmentScope
    fun provideBinding(@ApplicationContext context: Context): FragmentPostBinding {
        return FragmentPostBinding.inflate(LayoutInflater.from(context), null, false)
    }
}