package io.github.jesterz91.daggersample.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.jesterz91.daggersample.App
import io.github.jesterz91.daggersample.R
import io.github.jesterz91.daggersample.di.component.MainActivityComponent
import io.github.jesterz91.daggersample.di.module.MainActivityModule
import io.github.jesterz91.daggersample.util.Logger
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main), Logger {

    val mainActivityComponent: MainActivityComponent by lazy {
        (application as App).appComponent
            .mainActivityComponentBuilder()
            .setActivity(this)
            .setModule(MainActivityModule)
            .build()
    }

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var activityName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivityComponent.inject(this)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment())
            .commit()

        debug("sharedPreferences: $sharedPreferences")
        debug("activityName: $activityName")
    }
}