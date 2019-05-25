package com.lazyassevs.haveyoumet.ui.viewmodel

import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.lazyassevs.domain.sealedclass.ProviderResult
import com.lazyassevs.haveyoumet.util.base.activityresult.StartActivityForResultViewModel
import javax.inject.Inject

class GoogleSignInViewModel @Inject constructor(
    private val googleSignInClient: GoogleSignInClient
) : StartActivityForResultViewModel<GoogleSignInAccount>() {

    override val requestCode: Int
        get() = 1

    fun signIn() {
        start(googleSignInClient.signInIntent)
    }

    override fun handleResult(resultCode: Int, data: Intent?) {
        try {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)
            trigger.value = ProviderResult.Success(account!!)
        } catch (e: ApiException) {
            trigger.value = ProviderResult.Error(e)
        }
    }

}