package com.lazyassevs.haveyoumet.ui.view.album.list

import com.lazyassevs.domain.model.Album
import timber.log.Timber

sealed class Change {

    object AlbumListShowLoading : Change()

    object AlbumListHideLoading : Change()

    object ClearSingleEvent : Change()

    data class AlbumList(val albums: List<Album>) : Change()

    data class Error(val error: Throwable) : Change() {
        init {
            Timber.e(error)
        }
    }
}