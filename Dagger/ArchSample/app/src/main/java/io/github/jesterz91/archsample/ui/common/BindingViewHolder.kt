package io.github.jesterz91.archsample.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BindingViewHolder<B : ViewDataBinding>(
    parent: ViewGroup,
    @LayoutRes layoutId: Int,
    val binding: B = DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        layoutId,
        parent,
        false
    )
) : RecyclerView.ViewHolder(binding.root)