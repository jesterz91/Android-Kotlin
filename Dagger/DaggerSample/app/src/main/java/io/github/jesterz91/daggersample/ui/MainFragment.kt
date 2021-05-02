package io.github.jesterz91.daggersample.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import io.github.jesterz91.daggersample.R
import io.github.jesterz91.daggersample.di.module.MainFragmentModule
import io.github.jesterz91.daggersample.util.Logger
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main), Logger {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var activityName: String

    @set:[Inject]
    var number: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).mainActivityComponent
            .mainFragmentBuilder()
            .setFragment(this)
            .setModule(MainFragmentModule)
            .build()
            .inject(this)

        debug("sharedPreferences: $sharedPreferences")
        debug("activityName: $activityName")
        debug("number: $number")
    }
}