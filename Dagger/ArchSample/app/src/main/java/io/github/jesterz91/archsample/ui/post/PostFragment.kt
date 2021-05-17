package io.github.jesterz91.archsample.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import dagger.android.support.DaggerFragment
import io.github.jesterz91.archsample.databinding.FragmentPostBinding
import io.github.jesterz91.archsample.di.modules.app.viewmodel.ViewModelFactory
import io.github.jesterz91.archsample.extension.observe
import javax.inject.Inject

class PostFragment: DaggerFragment() {

    @Inject
    lateinit var binding: FragmentPostBinding

    @Inject
    lateinit var navController: NavController

    @Inject
    lateinit var postAdapter: PostAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val postViewModel: PostViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedInstanceState ?: postViewModel.loadPosts()

        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            viewModel = postViewModel
            postRecyclerView.run {
                setHasFixedSize(true)
                adapter = postAdapter
            }
        }

        observe(postViewModel.postsLiveData, postAdapter::setItems)

        observe(postViewModel.postClickEvent) { postItem ->
            val action: NavDirections = PostFragmentDirections.actionPostFragmentToPostDetailFragment(postItem.post)
            navController.navigate(action)
        }
    }
}