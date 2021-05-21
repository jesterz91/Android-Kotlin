package io.github.jesterz91.pagingwithnetwork.ui.main

import android.content.Intent
import android.os.Bundle
import io.github.jesterz91.pagingwithnetwork.databinding.ActivityMainBinding
import io.github.jesterz91.pagingwithnetwork.extension.clicks
import io.github.jesterz91.pagingwithnetwork.ui.common.BaseActivity
import io.github.jesterz91.pagingwithnetwork.ui.github.GithubActivity
import io.github.jesterz91.pagingwithnetwork.ui.reddit.RedditActivity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(ActivityMainBinding::inflate) {

    override val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val clickEvents = listOf(
            binding.github.clicks<GithubActivity>(),
            binding.reddit.clicks<RedditActivity>()
        )

        Observable.merge(clickEvents)
            .map { Intent(this, it) }
            .subscribe(::startActivity)
            .addTo(disposables)
    }
}