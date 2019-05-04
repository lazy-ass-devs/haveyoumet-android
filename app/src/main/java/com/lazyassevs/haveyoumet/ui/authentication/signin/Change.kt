package com.lazyassevs.haveyoumet.ui.authentication.signin

import timber.log.Timber

sealed class Change {

    data class SignInError(val error: Throwable) : Change() {
        init {
            Timber.e(error)
        }
    }

    object SignInSuccess : Change()

    object SigningIn : Change()

    object ReturnToSignIn : Change()

}