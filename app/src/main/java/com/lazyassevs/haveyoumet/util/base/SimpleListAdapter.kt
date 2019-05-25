package com.lazyassevs.haveyoumet.util.base

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lazyassevs.haveyoumet.R
import com.lazyassevs.haveyoumet.util.extensions.withBinding

abstract class SimpleListAdapter<T, B : ViewDataBinding>(
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, SimpleListAdapter.ViewHolder<B>>(diffCallback) {

    abstract fun bind(holder: ViewHolder<B>, item: T)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<B> =
        ViewHolder(parent.withBinding(R.layout.item_album))

    override fun onBindViewHolder(holder: ViewHolder<B>, position: Int) {
        bind(holder, getItem(position))
        holder.binding.executePendingBindings()
    }

    class ViewHolder<B : ViewDataBinding>(val binding: B) : RecyclerView.ViewHolder(binding.root)

}