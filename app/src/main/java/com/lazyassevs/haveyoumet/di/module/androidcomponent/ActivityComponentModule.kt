package com.lazyassevs.haveyoumet.di.module.androidcomponent

import com.lazyassevs.haveyoumet.di.component.ActivitySubComponent
import com.lazyassevs.haveyoumet.ui.authentication.AuthenticationActivity
import com.lazyassevs.haveyoumet.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
    subcomponents = [
        ActivitySubComponent::class
    ]
)
abstract class ActivityComponentModule {

    @ContributesAndroidInjector
    abstract fun splashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun authenticationActivity(): AuthenticationActivity

}