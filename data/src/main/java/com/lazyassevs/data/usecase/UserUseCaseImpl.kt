package com.lazyassevs.data.usecase

import com.lazyassevs.domain.usecase.UserUseCase
import io.reactivex.Single
import javax.inject.Inject

class UserUseCaseImpl @Inject constructor() : UserUseCase {

    override fun signIn(emailAddress: String, password: String): Single<Boolean> = Single.just(true)

    override fun isUserLoggedIn(): Single<Boolean> = Single.just(false)

}