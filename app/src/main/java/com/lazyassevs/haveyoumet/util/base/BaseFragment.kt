package com.lazyassevs.haveyoumet.util.base

import android.content.Intent
import com.lazyassevs.haveyoumet.util.base.activityresult.ActivityNavigation
import com.lazyassevs.haveyoumet.util.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment(), ActivityNavigation {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun activityForResult(intent: Intent?, requestCode: Int) {
        startActivityForResult(intent, requestCode)
    }

}