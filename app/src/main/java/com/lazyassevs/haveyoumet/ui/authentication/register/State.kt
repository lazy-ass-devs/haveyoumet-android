package com.lazyassevs.haveyoumet.ui.authentication.register

import com.lazyassevs.haveyoumet.util.base.mvi.BaseState

data class State(
    val isRegisterSuccess: Boolean = false,
    val isLoading: Boolean = false,
    val error: Throwable? = null
) : BaseState {

    val isFormVisible: Boolean
        get() = !isLoading && error == null

    val isErrorVisible: Boolean
        get() = error != null

    val errorMessage: String?
        get() = error?.localizedMessage

}