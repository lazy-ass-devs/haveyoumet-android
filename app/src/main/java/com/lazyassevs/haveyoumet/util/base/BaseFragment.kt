package com.lazyassevs.haveyoumet.util.base

import com.lazyassevs.haveyoumet.util.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

}