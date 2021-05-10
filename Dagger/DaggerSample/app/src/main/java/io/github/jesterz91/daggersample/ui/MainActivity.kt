package io.github.jesterz91.daggersample.ui

import android.content.SharedPreferences
import android.os.Bundle
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import io.github.jesterz91.daggersample.R
import io.github.jesterz91.daggersample.util.Logger
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(R.layout.activity_main), Logger {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var activityName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment())
            .commit()

        debug("sharedPreferences: $sharedPreferences")
        debug("activityName: $activityName")
    }
}