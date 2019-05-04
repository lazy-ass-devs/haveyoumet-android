package com.lazyassevs.haveyoumet.ui.authentication.signin

import com.lazyassevs.haveyoumet.util.base.mvi.BaseAction

sealed class Action : BaseAction {

    data class DoSignIn(val emailAddress: String, val password: String) : Action()

    object TrySignInAgain : Action()

}