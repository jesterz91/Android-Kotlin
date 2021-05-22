package io.github.jesterz91.navigation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import io.github.jesterz91.navigation.R
import io.github.jesterz91.navigation.databinding.FragmentHomeBinding

class HomeFragment: BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind), View.OnClickListener {

    override val layoutResource: Int = R.layout.fragment_home

    private val navController: NavController by lazy { findNavController() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.navigateActionButton?.setOnClickListener(this@HomeFragment)
        binding?.navigateDestinationButton?.setOnClickListener(this@HomeFragment)
    }

    override fun onClick(v: View?) {
        binding?.run {
            when (v) {
                navigateDestinationButton -> {
                    val options = navOptions {
                        anim {
                            enter = R.anim.slide_in_right
                            exit = R.anim.slide_out_left
                            popEnter = R.anim.slide_in_left
                            popExit = R.anim.slide_out_right
                        }
                    }
                    navController.navigate(R.id.flowStepOneFragment, null, options)
                }
                navigateActionButton -> {
                    val action = HomeFragmentDirections.actionHomeFragmentToFlowStepOneFragment(1)
                    navController.navigate(action)
                }
            }
        }
    }
}