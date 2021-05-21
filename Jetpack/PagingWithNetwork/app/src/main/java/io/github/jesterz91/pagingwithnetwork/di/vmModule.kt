package io.github.jesterz91.pagingwithnetwork.di

import io.github.jesterz91.pagingwithnetwork.ui.github.GithubViewModel
import io.github.jesterz91.pagingwithnetwork.ui.main.MainViewModel
import io.github.jesterz91.pagingwithnetwork.ui.reddit.RedditViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {

    viewModel { MainViewModel() }

    viewModel { GithubViewModel(get()) }

    viewModel { RedditViewModel(get()) }
}