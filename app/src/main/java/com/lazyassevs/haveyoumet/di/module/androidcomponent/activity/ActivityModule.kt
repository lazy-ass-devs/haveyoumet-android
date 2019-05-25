package com.lazyassevs.haveyoumet.di.module.androidcomponent.activity

import android.app.Activity
import com.lazyassevs.haveyoumet.di.qualifier.AppActivity
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerAppCompatActivity

@Module
class ActivityModule {

    @Provides
    @AppActivity
    fun appActivity(daggerAppCompatActivity: DaggerAppCompatActivity): Activity = daggerAppCompatActivity

}