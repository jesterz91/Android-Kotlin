package io.github.jesterz91.archsample.di.modules.app

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.jesterz91.archsample.di.modules.fragment.PostDetailModule
import io.github.jesterz91.archsample.di.modules.fragment.PostModule
import io.github.jesterz91.archsample.di.modules.fragment.UserModule
import io.github.jesterz91.archsample.di.scope.FragmentScope
import io.github.jesterz91.archsample.ui.detail.PostDetailFragment
import io.github.jesterz91.archsample.ui.post.PostFragment
import io.github.jesterz91.archsample.ui.user.UserFragment

@Module
abstract class FragmentBindingModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [PostModule::class])
    abstract fun bindPostFragment(): PostFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [PostDetailModule::class])
    abstract fun bindPostDetailFragment(): PostDetailFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [UserModule::class])
    abstract fun bindUserFragment(): UserFragment
}