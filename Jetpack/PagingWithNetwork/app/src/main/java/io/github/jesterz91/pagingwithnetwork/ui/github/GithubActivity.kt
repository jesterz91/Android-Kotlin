package io.github.jesterz91.pagingwithnetwork.ui.github

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import io.github.jesterz91.pagingwithnetwork.databinding.ActivityGithubBinding
import io.github.jesterz91.pagingwithnetwork.extension.addDivider
import io.github.jesterz91.pagingwithnetwork.ui.common.BaseActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class GithubActivity :
    BaseActivity<GithubViewModel, ActivityGithubBinding>(ActivityGithubBinding::inflate) {

    override val viewModel by viewModel<GithubViewModel>()

    private val githubAdapter = GithubAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.repoList.apply {
            setHasFixedSize(true)
            addDivider()
            adapter = githubAdapter
        }

        lifecycleScope.launch {
            viewModel.pagingData.collectLatest(githubAdapter::submitData)
        }
    }
}