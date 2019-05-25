package com.lazyassevs.haveyoumet.util.databinding

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

object SwipeRefreshLayoutBindingAdapter {

    @JvmStatic
    @BindingAdapter("isRefreshing")
    fun isRefreshing(swipeRefreshLayout: SwipeRefreshLayout, isRefreshing: Boolean) {
        swipeRefreshLayout.post {
            swipeRefreshLayout.isRefreshing = isRefreshing
        }
    }

}