package io.github.jesterz91.archsample.ui.detail.data

import io.github.jesterz91.archsample.network.data.User

data class PostDetailUserItem(
    val user: User,
    val onClick: ((User) -> Unit)?
) : PostDetailItem() {

    override val type: Type = Type.User
}