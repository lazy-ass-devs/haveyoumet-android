package com.lazyassdev.remote.api

import com.lazyassdev.remote.model.response.PhotoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PhotoApi {

    @GET("albums/{album_id}/photos")
    fun getAlbumPhotos(@Path("album_id") albumId: Long): Single<List<PhotoResponse>>

    @GET("photos/{id}")
    fun getPhoto(@Path("id") id: Long): Single<PhotoResponse>

}