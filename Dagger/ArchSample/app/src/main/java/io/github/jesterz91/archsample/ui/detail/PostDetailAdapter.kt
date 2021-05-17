package io.github.jesterz91.archsample.ui.detail

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import io.github.jesterz91.archsample.BR
import io.github.jesterz91.archsample.ui.common.BindingViewHolder
import io.github.jesterz91.archsample.ui.detail.data.PostDetailItem
import javax.inject.Inject

class PostDetailAdapter @Inject constructor() :
    RecyclerView.Adapter<BindingViewHolder<ViewDataBinding>>() {

    private val items: MutableList<PostDetailItem> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder<ViewDataBinding> {
        return BindingViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BindingViewHolder<ViewDataBinding>, position: Int) {
        holder.binding.run {
            setVariable(BR.item, items[position])
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = items[position].type.layoutId

    fun setItems(items: List<PostDetailItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}