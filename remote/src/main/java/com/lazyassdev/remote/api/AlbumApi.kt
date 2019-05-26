package com.lazyassdev.remote.api

import com.lazyassdev.remote.model.response.AlbumResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumApi {

    @GET("albums")
    fun getAlbums(): Single<List<AlbumResponse>>

    @GET("albums/{id}")
    fun getAlbum(@Path("id") id: Long): Single<AlbumResponse>

}