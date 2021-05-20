package io.github.jesterz91.databinding

import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("sentimentIcon")
    fun ImageView.sentimentIcon(like: Int) {
        when {
            like > 7 -> setImageResource(R.drawable.ic_sentiment_very_satisfied_black_24dp)
            like > 3 -> setImageResource(R.drawable.ic_sentiment_satisfied_black_24dp)
            else -> setImageResource(R.drawable.ic_sentiment_neutral_black_24dp)
        }
    }

    @JvmStatic
    @BindingAdapter("progressScaled", "max", requireAll = true)
    fun ProgressBar.setProgress(likes: Int, max: Int) {
        progress = (likes * max / 10).coerceAtMost(max)
    }
}