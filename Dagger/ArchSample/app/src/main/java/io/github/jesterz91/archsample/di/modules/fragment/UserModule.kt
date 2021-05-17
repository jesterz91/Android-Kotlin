package io.github.jesterz91.archsample.di.modules.fragment

import android.content.Context
import android.view.LayoutInflater
import dagger.Module
import dagger.Provides
import io.github.jesterz91.archsample.databinding.FragmentUserBinding
import io.github.jesterz91.archsample.di.qualifier.ApplicationContext
import io.github.jesterz91.archsample.di.scope.FragmentScope

@Module
object UserModule {

    @Provides
    @FragmentScope
    fun provideBinding(@ApplicationContext context: Context): FragmentUserBinding {
        return FragmentUserBinding.inflate(LayoutInflater.from(context), null, false)
    }
}