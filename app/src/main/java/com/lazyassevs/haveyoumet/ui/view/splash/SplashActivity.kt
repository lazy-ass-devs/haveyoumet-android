package com.lazyassevs.haveyoumet.ui.view.splash

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.lazyassevs.haveyoumet.R
import com.lazyassevs.haveyoumet.databinding.ActivitySplashBinding
import com.lazyassevs.haveyoumet.ui.view.album.AlbumActivity
import com.lazyassevs.haveyoumet.ui.view.authentication.AuthenticationActivity
import com.lazyassevs.haveyoumet.util.base.BaseActivity
import com.lazyassevs.haveyoumet.util.extensions.observe
import com.lazyassevs.haveyoumet.util.extensions.withBinding
import com.lazyassevs.haveyoumet.util.extensions.withViewModel
import timber.log.Timber

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        withBinding<ActivitySplashBinding>(R.layout.activity_splash)
        withViewModel<SplashViewModel>(this, viewModelFactory) {
            observe(observableState, ::render)
            dispatch(Action.GetNextPage)
        }
    }

    private fun render(state: State) {
        when (state) {
            is State.Error -> showError()
            State.InitialState -> Timber.d("initial state")
            State.GoToAuthentication -> goToAuthentication()
            State.GoToHome -> goToHome()
        }
    }

    private fun showError() {
        AlertDialog.Builder(this)
            .setMessage(R.string.error_msg_fatal)
            .setCancelable(false)
            .setPositiveButton(R.string.btn_close) { _, _ ->
                finish()
            }
            .show()
    }

    private fun goToAuthentication() {
        startActivity(AuthenticationActivity.newIntent(this))
    }

    private fun goToHome() {
        startActivity(AlbumActivity.newIntent(this))
    }

}