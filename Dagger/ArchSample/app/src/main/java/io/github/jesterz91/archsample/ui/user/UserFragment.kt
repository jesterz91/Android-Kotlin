package io.github.jesterz91.archsample.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.android.support.DaggerFragment
import io.github.jesterz91.archsample.databinding.FragmentUserBinding
import io.github.jesterz91.archsample.di.modules.app.viewmodel.ViewModelFactory
import javax.inject.Inject

class UserFragment : DaggerFragment() {

    @Inject
    lateinit var binding: FragmentUserBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val userViewModel: UserViewModel by viewModels { viewModelFactory }

    private val args: UserFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedInstanceState ?: userViewModel.load(args.userId)

        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            viewModel = userViewModel
        }
    }
}