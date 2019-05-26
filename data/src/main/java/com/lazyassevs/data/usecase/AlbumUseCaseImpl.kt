package com.lazyassevs.data.usecase

import com.lazyassevs.data.repository.AlbumListRepository
import com.lazyassevs.domain.model.Album
import com.lazyassevs.domain.repository.Repository
import com.lazyassevs.domain.usecase.AlbumUseCase
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class AlbumUseCaseImpl @Inject constructor(
    private val albumListRepository: AlbumListRepository
) : AlbumUseCase {

    override fun getAlbumList(): Observable<List<Album>> = albumListRepository.get(Repository.IGNORE_KEY)

    override fun fetchAlbumList(): Single<List<Album>> = albumListRepository.refresh(Repository.IGNORE_KEY)

    override fun getAlbum(id: Long): Observable<Album> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetchAlbum(id: Long): Single<Album> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}