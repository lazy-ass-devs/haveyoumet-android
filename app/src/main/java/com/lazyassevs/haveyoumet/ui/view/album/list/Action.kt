package com.lazyassevs.haveyoumet.ui.view.album.list

import com.lazyassevs.haveyoumet.util.base.mvi.BaseAction

sealed class Action : BaseAction {
    object GetAlbums : Action()
    object FetchAlbums : Action()
}