package io.github.jesterz91.pagingwithnetwork.ui.reddit

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import io.github.jesterz91.pagingwithnetwork.databinding.ActivityRedditBinding
import io.github.jesterz91.pagingwithnetwork.extension.addDivider
import io.github.jesterz91.pagingwithnetwork.ui.common.BaseActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RedditActivity : BaseActivity<RedditViewModel, ActivityRedditBinding>(ActivityRedditBinding::inflate) {

    override val viewModel by viewModel<RedditViewModel>()

    private val redditAdapter = RedditAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.subredditList.apply {
            addDivider()
            setHasFixedSize(true)
            adapter = redditAdapter
        }

        lifecycleScope.launch {
            viewModel.pagingData.collectLatest(redditAdapter::submitData)
        }
    }
}