package com.lazyassevs.haveyoumet.di.module.androidcomponent.activity

import android.app.Activity
import com.lazyassevs.haveyoumet.di.qualifier.AppActivity
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerFragment

@Module
class FragmentModule {

    @Provides
    @AppActivity
    fun appActivity(daggerFragment: DaggerFragment): Activity = daggerFragment.activity!!

}