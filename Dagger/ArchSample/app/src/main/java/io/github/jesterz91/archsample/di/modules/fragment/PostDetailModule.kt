package io.github.jesterz91.archsample.di.modules.fragment

import android.content.Context
import android.view.LayoutInflater
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.Module
import dagger.Provides
import io.github.jesterz91.archsample.databinding.FragmentPostDetailBinding
import io.github.jesterz91.archsample.di.qualifier.ApplicationContext
import io.github.jesterz91.archsample.di.scope.FragmentScope
import io.github.jesterz91.archsample.ui.detail.PostDetailFragment

@Module
object PostDetailModule {

    @Provides
    @FragmentScope
    fun provideBinding(@ApplicationContext context: Context): FragmentPostDetailBinding {
        return FragmentPostDetailBinding.inflate(LayoutInflater.from(context), null, false)
    }

    @Provides
    @FragmentScope
    fun provideNavController(fragment: PostDetailFragment): NavController {
        return NavHostFragment.findNavController(fragment)
    }
}