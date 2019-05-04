package com.lazyassevs.haveyoumet

import com.lazyassevs.haveyoumet.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber


class HaveYouMetApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent.factory()
        .create(this)

    override fun onCreate() {
        super.onCreate()

        setupDebugTools()
    }

    private fun setupDebugTools() {
        Timber.plant(Timber.DebugTree())
    }

}