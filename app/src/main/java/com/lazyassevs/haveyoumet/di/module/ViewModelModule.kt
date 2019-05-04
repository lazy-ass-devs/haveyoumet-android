package com.lazyassevs.haveyoumet.di.module

import androidx.lifecycle.ViewModel
import com.lazyassevs.haveyoumet.di.mapkey.ViewModelKey
import com.lazyassevs.haveyoumet.ui.authentication.register.RegisterFormViewModel
import com.lazyassevs.haveyoumet.ui.authentication.signin.SignInFormViewModel
import com.lazyassevs.haveyoumet.ui.authentication.signin.SignInViewModel
import com.lazyassevs.haveyoumet.ui.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun splashViewModel(splashViewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    abstract fun signInViewModel(signInViewModel: SignInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignInFormViewModel::class)
    abstract fun signInFormViewModel(signInFormViewModel: SignInFormViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterFormViewModel::class)
    abstract fun registerFormViewModel(registerFormViewModel: RegisterFormViewModel): ViewModel

}