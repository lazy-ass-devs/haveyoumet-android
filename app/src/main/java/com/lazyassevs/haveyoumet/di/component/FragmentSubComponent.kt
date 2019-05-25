package com.lazyassevs.haveyoumet.di.component

import com.lazyassevs.haveyoumet.di.module.androidcomponent.activity.ActivityViewModelModule
import com.lazyassevs.haveyoumet.di.module.androidcomponent.activity.FragmentModule
import com.lazyassevs.haveyoumet.di.module.androidcomponent.activity.GoogleSignInModule
import com.lazyassevs.haveyoumet.di.scope.PerActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerFragment

@PerActivity
@Subcomponent(
    modules = [
        FragmentModule::class,
        GoogleSignInModule::class,
        ActivityViewModelModule::class
    ]
)
interface FragmentSubComponent : AndroidInjector<DaggerFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<DaggerFragment>

}