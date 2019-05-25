package com.lazyassevs.domain.usecase

import com.lazyassevs.domain.model.Album
import io.reactivex.Observable
import io.reactivex.Single

interface AlbumUseCase {

    fun getAlbumList(): Observable<List<Album>>

    fun fetchAlbumList(): Single<List<Album>>

    fun getAlbum(id: Long): Observable<Album>

    fun fetchAlbum(id: Long): Single<Album>

}