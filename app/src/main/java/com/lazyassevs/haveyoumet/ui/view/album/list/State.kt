package com.lazyassevs.haveyoumet.ui.view.album.list

import com.lazyassevs.domain.model.Album
import com.lazyassevs.haveyoumet.util.base.mvi.BaseState

data class State(
    val albums: List<Album> = emptyList(),
    val isRefreshing: Boolean = false,
    val error: Throwable? = null
) : BaseState