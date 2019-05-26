package com.lazyassdev.remote.module

import com.lazyassdev.remote.api.AlbumApi
import com.lazyassdev.remote.api.PhotoApi
import com.lazyassevs.domain.model.Album
import com.lazyassevs.domain.model.Photo
import com.lazyassevs.domain.source.RemoteSource
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

@Module
class RemoteSourceModule {

    @Provides
    fun albumListRemoteSource(
        albumApi: AlbumApi
    ): RemoteSource<Long, List<Album>> = object : RemoteSource<Long, List<Album>> {
        override fun get(key: Long): Single<List<Album>> = albumApi.getAlbums()
            .map { it.map { data -> Album(data.id, data.userId, data.title) } }
            .subscribeOn(Schedulers.io())

    }

    @Provides
    fun albumRemoteSource(
        albumApi: AlbumApi
    ): RemoteSource<Long, Album> = object : RemoteSource<Long, Album> {
        override fun get(key: Long): Single<Album> = albumApi.getAlbum(key)
            .map { Album(it.id, it.userId, it.title) }
            .subscribeOn(Schedulers.io())
    }

    @Provides
    fun photosRemoteSouce(
        photoApi: PhotoApi
    ): RemoteSource<Long, List<Photo>> = object : RemoteSource<Long, List<Photo>> {
        override fun get(key: Long): Single<List<Photo>> = photoApi.getAlbumPhotos(key)
            .map { it.map { data -> Photo(data.id, data.albumId, data.title, data.url, data.thumbnailUrl) } }
            .subscribeOn(Schedulers.io())
    }

    @Provides
    fun photoListResponse(
        photoApi: PhotoApi
    ): RemoteSource<Long, Photo> = object : RemoteSource<Long, Photo> {
        override fun get(key: Long): Single<Photo> = photoApi.getPhoto(key)
            .map { Photo(it.id, it.albumId, it.title, it.url, it.thumbnailUrl) }
    }

}