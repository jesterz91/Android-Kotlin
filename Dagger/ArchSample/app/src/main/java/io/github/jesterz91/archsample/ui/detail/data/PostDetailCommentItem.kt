package io.github.jesterz91.archsample.ui.detail.data

import io.github.jesterz91.archsample.network.data.Comment

data class PostDetailCommentItem(val comment: Comment) : PostDetailItem() {

    override val type: Type = Type.Comment
}