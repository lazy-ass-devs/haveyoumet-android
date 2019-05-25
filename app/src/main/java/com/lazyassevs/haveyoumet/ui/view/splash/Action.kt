package com.lazyassevs.haveyoumet.ui.view.splash

import com.lazyassevs.haveyoumet.util.base.mvi.BaseAction

sealed class Action : BaseAction {

    object GetNextPage : Action()

}