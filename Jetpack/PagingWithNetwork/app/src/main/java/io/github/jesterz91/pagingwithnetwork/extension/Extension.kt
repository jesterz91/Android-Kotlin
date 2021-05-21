package io.github.jesterz91.pagingwithnetwork.extension

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.core.Observable

@BindingAdapter("loadUrl")
fun ImageView.loadUrl(url: String?) {
    url?.let {
        Glide.with(this).load(url).into(this)
    }
}

fun RecyclerView.addDivider(orientation: Int = DividerItemDecoration.VERTICAL) {
    val deco = DividerItemDecoration(context, orientation)
    addItemDecoration(deco)
}


inline fun <reified T> View.clicks(): Observable<Class<T>> = clicks().map { T::class.java }