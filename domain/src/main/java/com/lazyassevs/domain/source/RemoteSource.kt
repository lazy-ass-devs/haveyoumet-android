package com.lazyassevs.domain.source

import io.reactivex.Single

interface RemoteSource<Key, Data> {

    fun get(key: Key): Single<Data>

}