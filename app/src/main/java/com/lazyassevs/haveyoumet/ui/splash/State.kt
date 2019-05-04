package com.lazyassevs.haveyoumet.ui.splash

import com.lazyassevs.haveyoumet.util.base.mvi.BaseState
import timber.log.Timber

sealed class State : BaseState {

    data class Error(val error: Throwable) : State() {
        init {
            Timber.e(error)
        }
    }

    object InitialState : State()

    object GoToAuthentication : State()

    object GoToHome : State()

}