package com.lazyassevs.haveyoumet.util.extensions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * This is an extension function that calls to [DataBindingUtil.setContentView].
 * Gets the data binding for layout
 */
fun <B : ViewDataBinding> FragmentActivity.withBinding(
    @LayoutRes layoutResId: Int,
    function: B.() -> Unit = {}
): B {
    val binding: B = DataBindingUtil.setContentView(this, layoutResId)
    binding.lifecycleOwner = this
    binding.function()
    return binding
}

/**
 * This is an extension function that calls to [DataBindingUtil.inflate].
 * Gets the data binding for layout
 */
fun <B : ViewDataBinding> Fragment.withBinding(
    inflater: LayoutInflater, @LayoutRes layoutResId: Int,
    parent: ViewGroup?, function: B.() -> Unit = {}
): B {
    val binding: B = DataBindingUtil.inflate(inflater, layoutResId, parent, false)
    binding.lifecycleOwner = this
    binding.function()
    return binding
}

/**
 * This is an extension function that calls to [DataBindingUtil.inflate].
 * Gets the data binding for layout
 */
fun <B : ViewDataBinding> ViewGroup.withBinding(@LayoutRes layoutResId: Int): B =
    DataBindingUtil.inflate(LayoutInflater.from(context), layoutResId, this, false)