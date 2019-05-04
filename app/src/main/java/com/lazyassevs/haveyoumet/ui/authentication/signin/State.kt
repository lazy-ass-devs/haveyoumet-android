package com.lazyassevs.haveyoumet.ui.authentication.signin

import com.lazyassevs.haveyoumet.util.base.mvi.BaseState

data class State(
    val isSignInSuccess: Boolean = false,
    val isSigningIn: Boolean = false,
    val error: Throwable? = null
) : BaseState {

    val isInputLayoutVisible: Boolean
        get() = !isSigningIn && error == null

    val isErrorLayoutVisible: Boolean
        get() = error != null

    val errorMessage: String?
        get() = error?.localizedMessage

}