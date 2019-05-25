package com.lazyassevs.data.module

import com.lazyassevs.data.usecase.AlbumUseCaseImpl
import com.lazyassevs.data.usecase.UserUseCaseImpl
import com.lazyassevs.domain.usecase.AlbumUseCase
import com.lazyassevs.domain.usecase.UserUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun userUseCase(userUseCaseImpl: UserUseCaseImpl): UserUseCase = userUseCaseImpl

    @Provides
    fun albumUseCase(albumUseCaseImpl: AlbumUseCaseImpl): AlbumUseCase = albumUseCaseImpl

}