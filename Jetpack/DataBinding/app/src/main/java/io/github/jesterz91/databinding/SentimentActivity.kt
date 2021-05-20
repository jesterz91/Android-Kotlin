package io.github.jesterz91.databinding

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import io.github.jesterz91.databinding.databinding.ActivitySentimentBinding

class SentimentActivity : AppCompatActivity() {

    private val sentimentViewModel: SentimentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySentimentBinding.inflate(layoutInflater).apply {
            viewModel = sentimentViewModel
            lifecycleOwner = this@SentimentActivity
        }
        setContentView(binding.root)
    }
}
