package io.github.jesterz91.daggersample.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import io.github.jesterz91.daggersample.R
import io.github.jesterz91.daggersample.util.Logger
import javax.inject.Inject

class MainFragment : DaggerFragment(R.layout.fragment_main), Logger {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var activityName: String

    @set:[Inject]
    var number: Int = 0

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        debug("sharedPreferences: $sharedPreferences")
        debug("activityName: $activityName")
        debug("number: $number")
    }
}