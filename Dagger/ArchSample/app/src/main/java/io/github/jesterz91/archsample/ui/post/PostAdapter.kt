package io.github.jesterz91.archsample.ui.post

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.jesterz91.archsample.BR
import io.github.jesterz91.archsample.R
import io.github.jesterz91.archsample.databinding.ItemPostBinding
import io.github.jesterz91.archsample.ui.common.BindingViewHolder
import javax.inject.Inject

class PostAdapter @Inject constructor() : RecyclerView.Adapter<BindingViewHolder<ItemPostBinding>>() {

    private val items: MutableList<PostItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<ItemPostBinding> {
        return BindingViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BindingViewHolder<ItemPostBinding>, position: Int) {
        with(holder.binding) {
            setVariable(BR.item, items[position])
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = R.layout.item_post

    fun setItems(items: List<PostItem>) {
        this.items.clear()
        this.items.addAll(items)
        this.notifyDataSetChanged()
    }
}