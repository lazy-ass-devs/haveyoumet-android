package com.lazyassevs.haveyoumet.di

import com.lazyassevs.data.module.UseCaseModule
import com.lazyassevs.haveyoumet.di.module.AndroidComponentModule
import com.lazyassevs.haveyoumet.di.module.AppModule
import com.lazyassevs.haveyoumet.di.module.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidComponentModule::class,
        AppModule::class,
        ViewModelModule::class,
        UseCaseModule::class
    ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<DaggerApplication>

}