package io.github.jesterz91.navigation.fragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import io.github.jesterz91.navigation.R
import io.github.jesterz91.navigation.databinding.FragmentFlowStepBinding

class FlowStepFragment : BaseFragment<FragmentFlowStepBinding>(FragmentFlowStepBinding::bind),
    View.OnClickListener {

    private val safeArgs: FlowStepFragmentArgs by navArgs()

    private val navController by lazy { findNavController() }

    override val layoutResource: Int = R.layout.fragment_flow_step

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.text?.text = "FlowStep ${safeArgs.flowStepNumber}"

        binding?.nextButton?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (safeArgs.flowStepNumber) {
            1 -> navController.navigate(R.id.action_flowStepOneFragment_to_flowStepTwoFragment)
            2 -> navController.navigate(R.id.action_flowStepTwoFragment_to_homeFragment, bundleOf(FLOW_STEP_NUMBER to 2))
        }
    }

    companion object {
        const val FLOW_STEP_NUMBER = "flowStepNumber"
    }
}
