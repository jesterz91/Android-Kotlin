package io.github.jesterz91.archsample.ui

import android.os.Bundle
import dagger.Lazy
import dagger.android.support.DaggerAppCompatActivity
import io.github.jesterz91.archsample.databinding.ActivityMainBinding
import io.github.jesterz91.archsample.util.Logger
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), Logger {

    @Inject
    lateinit var binding: Lazy<ActivityMainBinding>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.get().lifecycleOwner = this
        debug("MainActivity Created")
    }
}