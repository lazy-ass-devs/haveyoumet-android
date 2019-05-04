package com.lazyassevs.haveyoumet.di.module.androidcomponent.activity

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.lazyassevs.haveyoumet.di.scope.PerActivity
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerAppCompatActivity

@Module
class GoogleSignInModule {

    @Provides
    @PerActivity
    fun googleSignInOptions(): GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build()

    @Provides
    @PerActivity
    fun googleSignInClient(
        daggerAppCompatActivity: DaggerAppCompatActivity,
        googleSignInOptions: GoogleSignInOptions
    ): GoogleSignInClient = GoogleSignIn.getClient(daggerAppCompatActivity, googleSignInOptions)


}