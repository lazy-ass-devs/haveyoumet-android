package com.lazyassevs.haveyoumet.util.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <M : Any, L : LiveData<M>> LifecycleOwner.observe(liveData: L, observer: (M) -> Unit = {}) =
    liveData.observe(this, Observer(observer))