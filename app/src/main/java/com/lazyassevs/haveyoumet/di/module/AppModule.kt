package com.lazyassevs.haveyoumet.di.module

import android.app.Application
import com.lazyassevs.haveyoumet.di.qualifier.App
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerApplication

@Module
class AppModule {

    @Provides
    @App
    fun app(daggerApplication: DaggerApplication): Application = daggerApplication

}