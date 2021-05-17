package io.github.jesterz91.archsample.ui.detail.data

import io.github.jesterz91.archsample.network.data.Post

data class PostDetailPostItem(val post: Post) : PostDetailItem() {

    override val type: Type = Type.Post
}