package com.lazyassevs.domain.repository

import com.lazyassevs.domain.exceptions.NoRecordsFoundException
import com.lazyassevs.domain.source.LocalSource
import com.lazyassevs.domain.source.RemoteSource
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Single
import io.reactivex.functions.Function

abstract class Repository<Key, Data>(
    private val localSource: LocalSource<Key, Data>,
    private val remoteSource: RemoteSource<Key, Data>
) {

    companion object {
        const val IGNORE_KEY = 0L
    }

    private val isInitialDataLoaded = mutableMapOf<Key, Boolean>()

    /**
     * query from local database,
     */
    fun get(key: Key): Observable<Data> = localSource.get(key)
        .take(1)
        .onErrorResumeNext(errorResumeFunction(key))
        .switchMap { data: Data ->
            refresh(key)
                .toObservable()
                .switchMap { response ->
                    localSource.get(key)
                        .startWith(response)
                }
                .startWith(data)
        }

    /**
     * Trigger fetch data from network and save result
     */
    fun refresh(key: Key): Single<Data> = remoteSource.get(key)
        .flatMap { localSource.save(key, it) }
        .doFinally { isInitialDataLoaded[key] = false }

    private fun errorResumeFunction(key: Key): Function<Throwable, ObservableSource<out Data>> =
        Function { error ->
            when {
                error is NoRecordsFoundException && isInitialDataLoaded[key] ?: true -> refresh(key)
                    .toObservable()
                    .flatMap { parsed ->
                        localSource.get(key)
                            .startWith(parsed)
                    }
                else -> throw error
            }
        }

}