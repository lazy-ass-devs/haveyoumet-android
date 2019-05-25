package com.lazyassevs.haveyoumet.util.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.lazyassevs.domain.model.Album

object AlbumItemCallback : DiffUtil.ItemCallback<Album>() {
    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean = oldItem == newItem
}