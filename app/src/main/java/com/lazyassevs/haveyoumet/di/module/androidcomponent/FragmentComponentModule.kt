package com.lazyassevs.haveyoumet.di.module.androidcomponent

import com.lazyassevs.haveyoumet.ui.authentication.signin.SignInFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentComponentModule {

    @ContributesAndroidInjector
    abstract fun signInFragment(): SignInFragment

}