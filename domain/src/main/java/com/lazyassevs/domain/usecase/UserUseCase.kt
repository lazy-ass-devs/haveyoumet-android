package com.lazyassevs.domain.usecase

import io.reactivex.Single

interface UserUseCase {

    fun isUserLoggedIn(): Single<Boolean>

    fun signIn(emailAddress: String, password: String): Single<Boolean>

}