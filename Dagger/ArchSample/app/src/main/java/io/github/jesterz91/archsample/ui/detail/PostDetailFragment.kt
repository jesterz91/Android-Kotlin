package io.github.jesterz91.archsample.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.navArgs
import dagger.android.support.DaggerFragment
import io.github.jesterz91.archsample.databinding.FragmentPostDetailBinding
import io.github.jesterz91.archsample.di.modules.app.viewmodel.ViewModelFactory
import io.github.jesterz91.archsample.extension.observe
import io.github.jesterz91.archsample.util.Logger
import javax.inject.Inject

class PostDetailFragment : DaggerFragment(), Logger {

    @Inject
    lateinit var binding: FragmentPostDetailBinding

    @Inject
    lateinit var navController: NavController

    @Inject
    lateinit var postDetailAdapter: PostDetailAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val postDetailViewModel: PostDetailViewModel by viewModels { viewModelFactory }

    private val args: PostDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedInstanceState ?: postDetailViewModel.load(args.post)

        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            viewModel = postDetailViewModel
            postDetailRecyclerView.run {
                setHasFixedSize(true)
                adapter = postDetailAdapter
            }
        }

        observe(postDetailViewModel.postDetailLiveData, postDetailAdapter::setItems)

        observe(postDetailViewModel.postDetailClickEvent) { user ->
            debug("user: $user")
        }
    }
}