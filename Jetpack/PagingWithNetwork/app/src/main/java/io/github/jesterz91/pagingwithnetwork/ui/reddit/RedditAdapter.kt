package io.github.jesterz91.pagingwithnetwork.ui.reddit

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import io.github.jesterz91.pagingwithnetwork.R
import io.github.jesterz91.pagingwithnetwork.data.model.reddit.RedditPost
import io.github.jesterz91.pagingwithnetwork.databinding.ItemRedditBinding
import io.github.jesterz91.pagingwithnetwork.ui.common.BindingViewHolder

class RedditAdapter : PagingDataAdapter<RedditPost, BindingViewHolder<ItemRedditBinding>>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<ItemRedditBinding> {
        return BindingViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BindingViewHolder<ItemRedditBinding>, position: Int) {
        val post = getItem(position)
        if (post != null) {
            holder.binding.item = post
            holder.binding.executePendingBindings()
        }
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_reddit

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RedditPost>() {
            override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
                return oldItem == newItem
            }
        }
    }
}