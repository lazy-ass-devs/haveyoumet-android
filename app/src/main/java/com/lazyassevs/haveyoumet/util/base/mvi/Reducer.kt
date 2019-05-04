package com.lazyassevs.haveyoumet.util.base.mvi

typealias Reducer<S, C> = (state: S, change: C) -> S