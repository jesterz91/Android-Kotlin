package io.github.jesterz91.pagingwithroom.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.jesterz91.pagingwithroom.databinding.ItemCheeseBinding
import io.github.jesterz91.pagingwithroom.db.Cheese

class CheeseAdapter : PagingDataAdapter<Cheese, CheeseAdapter.CheeseViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheeseViewHolder {
        return CheeseViewHolder(ItemCheeseBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CheeseViewHolder, position: Int) {
        getItem(position)?.run(holder::bind)
    }

    override fun onViewRecycled(holder: CheeseViewHolder) {
        holder.unbind()
        super.onViewRecycled(holder)
    }

    inner class CheeseViewHolder(private val binding: ItemCheeseBinding) : RecyclerView.ViewHolder(binding.root) {

        var cheese: Cheese? = null

        fun bind(cheese: Cheese) {
            this.cheese = cheese
            binding.name.text = "${cheese.id}. ${cheese.name}"
        }

        fun unbind() {
            cheese = null
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Cheese>() {
            override fun areItemsTheSame(oldItem: Cheese, newItem: Cheese): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Cheese, newItem: Cheese): Boolean =
                oldItem == newItem
        }
    }
}