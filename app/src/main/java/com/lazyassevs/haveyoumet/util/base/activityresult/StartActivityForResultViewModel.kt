package com.lazyassevs.haveyoumet.util.base.activityresult

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lazyassevs.domain.sealedclass.ProviderResult
import com.lazyassevs.haveyoumet.util.livedata.LiveMessageEvent

abstract class StartActivityForResultViewModel<T> : ViewModel() {

    abstract val requestCode: Int

    abstract fun handleResult(resultCode: Int, data: Intent?)

    protected val trigger = MutableLiveData<ProviderResult<T>>()

    val result: LiveData<ProviderResult<T>> = trigger

    val activityNavigationEvent = LiveMessageEvent<ActivityNavigation>()

    protected fun start(intent: Intent) {
        activityNavigationEvent.sendEvent {
            activityForResult(intent, requestCode)
        }
    }

    fun onResultFromActivity(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            requestCode -> handleResult(resultCode, data)
        }
    }

}