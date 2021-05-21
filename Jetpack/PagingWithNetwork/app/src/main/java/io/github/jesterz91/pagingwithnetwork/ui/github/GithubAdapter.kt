package io.github.jesterz91.pagingwithnetwork.ui.github

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import io.github.jesterz91.pagingwithnetwork.R
import io.github.jesterz91.pagingwithnetwork.data.model.github.Repo
import io.github.jesterz91.pagingwithnetwork.databinding.ItemRepoBinding
import io.github.jesterz91.pagingwithnetwork.ui.common.BindingViewHolder

class GithubAdapter : PagingDataAdapter<Repo, BindingViewHolder<ItemRepoBinding>>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<ItemRepoBinding> {
        return BindingViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BindingViewHolder<ItemRepoBinding>, position: Int) {
        val repo = getItem(position)
        if (repo != null) {
            holder.binding.item = repo
            holder.binding.executePendingBindings()
        }
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_repo

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem == newItem
            }
        }
    }
}