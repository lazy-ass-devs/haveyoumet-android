package com.lazyassevs.haveyoumet.di.component

import com.lazyassevs.haveyoumet.di.module.androidcomponent.activity.ActivityModule
import com.lazyassevs.haveyoumet.di.module.androidcomponent.activity.ActivityViewModelModule
import com.lazyassevs.haveyoumet.di.module.androidcomponent.activity.GoogleSignInModule
import com.lazyassevs.haveyoumet.di.scope.PerActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerAppCompatActivity

@PerActivity
@Subcomponent(
    modules = [
        ActivityModule::class,
        GoogleSignInModule::class,
        ActivityViewModelModule::class
    ]
)
interface ActivitySubComponent : AndroidInjector<DaggerAppCompatActivity> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<DaggerAppCompatActivity>

}