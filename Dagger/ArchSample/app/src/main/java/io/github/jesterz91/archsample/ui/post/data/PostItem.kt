package io.github.jesterz91.archsample.ui.post.data

import io.github.jesterz91.archsample.network.data.Post

data class PostItem(
    val post: Post,
    val onPostClick: ((postItem: PostItem) -> Unit)? = null
)
