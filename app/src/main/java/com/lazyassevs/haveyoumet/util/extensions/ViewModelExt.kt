package com.lazyassevs.haveyoumet.util.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

/**
 * This is an extension function that calls [ViewModelProviders.of]
 * Gets the view model (activity scope)
 */
inline fun <reified VM : ViewModel> withViewModel(
    fragmentActivity: FragmentActivity,
    factory: ViewModelProvider.Factory,
    function: VM.() -> Unit = {}
): VM {
    val vm = ViewModelProviders.of(fragmentActivity, factory)[VM::class.java]
    vm.function()
    return vm
}

/**
 * This is an extension function that calls [ViewModelProviders.of]
 * Gets the view model (fragment scope)
 */
inline fun <reified VM : ViewModel> withViewModel(
    fragment: Fragment,
    factory: ViewModelProvider.Factory,
    function: VM.() -> Unit = {}
): VM {
    val vm = ViewModelProviders.of(fragment, factory)[VM::class.java]
    vm.function()
    return vm
}