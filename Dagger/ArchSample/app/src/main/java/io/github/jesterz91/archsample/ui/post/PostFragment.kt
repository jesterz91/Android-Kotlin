package io.github.jesterz91.archsample.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import io.github.jesterz91.archsample.databinding.FragmentPostBinding
import io.github.jesterz91.archsample.di.modules.app.viewmodel.ViewModelFactory
import io.github.jesterz91.archsample.extension.observe
import io.github.jesterz91.archsample.util.Logger
import javax.inject.Inject

class PostFragment: DaggerFragment(), Logger {

    @Inject
    lateinit var binding: FragmentPostBinding

    @Inject
    lateinit var postAdapter: PostAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var postViewModel: PostViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        postViewModel = ViewModelProvider(this, viewModelFactory)[PostViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedInstanceState ?: postViewModel.loadPosts()

        debug("PostFragment Created")

        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            viewModel = postViewModel
            postRecyclerView.run {
                setHasFixedSize(true)
                adapter = postAdapter
            }
        }

        observe(postViewModel.postsLiveData, postAdapter::setItems)
    }
}