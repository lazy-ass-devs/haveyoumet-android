package com.lazyassevs.haveyoumet.util.base.activityresult

import android.content.Intent

interface ActivityNavigation {
    fun activityForResult(intent: Intent?, requestCode: Int)
}