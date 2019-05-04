package com.lazyassevs.haveyoumet.util.databinding

import android.view.View
import androidx.databinding.BindingAdapter
import com.lazyassevs.haveyoumet.util.extensions.makeGone
import com.lazyassevs.haveyoumet.util.extensions.makeInvisible
import com.lazyassevs.haveyoumet.util.extensions.makeVisible

object ViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("visibleGone")
    fun visibleGone(view: View, isVisible: Boolean) {
        if (isVisible) view.makeVisible() else view.makeGone()
    }

    @JvmStatic
    @BindingAdapter("visibleHidden")
    fun visibleHidden(view: View, isVisible: Boolean) {
        if (isVisible) view.makeVisible() else view.makeInvisible()
    }

}