package com.lazyassevs.haveyoumet.di.module

import com.lazyassevs.haveyoumet.di.module.androidcomponent.ActivityComponentModule
import com.lazyassevs.haveyoumet.di.module.androidcomponent.FragmentComponentModule
import dagger.Module

@Module(
    includes = [
        ActivityComponentModule::class,
        FragmentComponentModule::class
    ]
)
abstract class AndroidComponentModule