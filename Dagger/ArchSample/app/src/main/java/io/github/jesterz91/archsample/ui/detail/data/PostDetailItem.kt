package io.github.jesterz91.archsample.ui.detail.data

import androidx.annotation.LayoutRes
import io.github.jesterz91.archsample.R

abstract class PostDetailItem {

    abstract val type: Type

    enum class Type(@LayoutRes val layoutId: Int) {
        User(R.layout.item_post_detail_user),
        Post(R.layout.item_post_detail_post),
        Comment(R.layout.item_post_detail_comment)
    }
}
