package com.lazyassevs.data.usecase

import com.lazyassevs.domain.model.Album
import com.lazyassevs.domain.usecase.AlbumUseCase
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class AlbumUseCaseImpl @Inject constructor() : AlbumUseCase {

    override fun getAlbumList(): Observable<List<Album>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetchAlbumList(): Single<List<Album>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAlbum(id: Long): Observable<Album> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetchAlbum(id: Long): Single<Album> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}