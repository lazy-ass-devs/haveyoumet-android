package com.lazyassevs.haveyoumet.ui.view.authentication.signin

import com.lazyassevs.domain.enum.SignInProvider
import com.lazyassevs.haveyoumet.util.base.mvi.BaseAction

sealed class Action : BaseAction {

    data class DoSignIn(val emailAddress: String, val password: String) : Action()

    data class DoSignInProvider(val provider: SignInProvider, val id: String) : Action()

    object TrySignInAgain : Action()

}