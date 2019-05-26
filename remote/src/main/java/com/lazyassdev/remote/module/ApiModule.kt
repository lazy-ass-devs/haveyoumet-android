package com.lazyassdev.remote.module

import com.lazyassdev.remote.api.AlbumApi
import com.lazyassdev.remote.api.PhotoApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun albumApi(retrofit: Retrofit): AlbumApi = retrofit.create(AlbumApi::class.java)

    @Singleton
    @Provides
    fun photoApi(retrofit: Retrofit): PhotoApi = retrofit.create(PhotoApi::class.java)

}