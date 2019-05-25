package com.lazyassevs.haveyoumet.ui.view.album.list

import com.lazyassevs.domain.usecase.AlbumUseCase
import com.lazyassevs.haveyoumet.util.base.mvi.BaseViewModel
import com.lazyassevs.haveyoumet.util.base.mvi.Reducer
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AlbumListViewModel @Inject constructor(
    private val albumUseCase: AlbumUseCase
) : BaseViewModel<Action, State>() {

    override val initialState: State
        get() = State()

    private val reducer: Reducer<State, Change> = { state, change ->
        when (change) {
            Change.AlbumListShowLoading -> state.copy(isRefreshing = true)
            Change.AlbumListHideLoading -> state.copy(isRefreshing = false)
            Change.ClearSingleEvent -> state.copy(error = null)
            is Change.AlbumList -> state.copy(albums = change.albums)
            is Change.Error -> state.copy(error = change.error)
        }
    }

    private val getAlbumsChange = actions.ofType<Action.GetAlbums>()
        .switchMap { getAlbumsObservable() }

    private val fetchAlbumsChange = actions.ofType<Action.FetchAlbums>()
        .switchMap { fetchAlbumsObservable() }

    init {
        val changes = Observable.mergeArray(getAlbumsChange, fetchAlbumsChange)
            .switchMap { change ->
                when (change) {
                    is Change.Error -> Observable.just<Change>(Change.ClearSingleEvent)
                        .startWith(change)
                    else -> Observable.just<Change>(change)
                }
            }
            .scan(initialState, reducer)
            .distinctUntilChanged()

        subscribe(changes)
    }

    private fun getAlbumsObservable(): Observable<Change> = albumUseCase.getAlbumList()
        .map<Change> { Change.AlbumList(it) }
        .startWith(Change.AlbumListShowLoading)
        .onErrorReturn { Change.Error(it) }
        .switchMap { change ->
            when (change) {
                Change.AlbumListShowLoading -> Observable.just(change)
                else -> Observable.just<Change>(Change.AlbumListHideLoading)
                    .startWith(change)
            }
        }
        .subscribeOn(Schedulers.io())

    private fun fetchAlbumsObservable(): Observable<Change> = albumUseCase.fetchAlbumList()
        .toObservable()
        .map<Change> { Change.AlbumListHideLoading }
        .startWith(Change.AlbumListShowLoading)
        .onErrorReturn { Change.Error(it) }
        .switchMap { change ->
            when (change) {
                is Change.Error -> Observable.just<Change>(Change.ClearSingleEvent)
                    .startWith(change)
                else -> Observable.just<Change>(change)
            }
        }
        .subscribeOn(Schedulers.io())

}