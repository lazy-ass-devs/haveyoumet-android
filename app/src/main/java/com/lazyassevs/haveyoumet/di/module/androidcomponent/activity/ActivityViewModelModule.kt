package com.lazyassevs.haveyoumet.di.module.androidcomponent.activity

import androidx.lifecycle.ViewModel
import com.lazyassevs.haveyoumet.di.mapkey.ViewModelKey
import com.lazyassevs.haveyoumet.ui.viewmodel.GoogleSignInViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ActivityViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GoogleSignInViewModel::class)
    abstract fun googleSignInViewModel(googleSignInViewModel: GoogleSignInViewModel): ViewModel

}