package com.lazyassevs.haveyoumet.ui.view.authentication.signin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.lazyassevs.domain.enum.SignInProvider
import com.lazyassevs.domain.sealedclass.ProviderResult
import com.lazyassevs.haveyoumet.R
import com.lazyassevs.haveyoumet.databinding.FragmentSignInBinding
import com.lazyassevs.haveyoumet.ui.viewmodel.GoogleSignInViewModel
import com.lazyassevs.haveyoumet.util.base.BaseFragment
import com.lazyassevs.haveyoumet.util.extensions.observe
import com.lazyassevs.haveyoumet.util.extensions.withBinding
import com.lazyassevs.haveyoumet.util.extensions.withViewModel
import timber.log.Timber

class SignInFragment : BaseFragment() {

    private lateinit var binding: FragmentSignInBinding

    private lateinit var viewModel: SignInViewModel
    private lateinit var formViewModel: SignInFormViewModel
    private lateinit var googleSignInViewModel: GoogleSignInViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = withBinding(inflater, R.layout.fragment_sign_in, container)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = withViewModel(this, viewModelFactory) {
            observe(observableState, ::render)
        }
        googleSignInViewModel = withViewModel(this, viewModelFactory) {
            activityNavigationEvent.setEventReceiver(this@SignInFragment, this@SignInFragment)
            observe(result, ::handleGoogleSignInResult)
        }

        formViewModel = withViewModel(this, viewModelFactory)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.form = formViewModel

        binding.apply {
            btnSignIn.setOnClickListener { onSignIn() }
            btnSignInGoogle.setOnClickListener { onSignInUsingGoogle() }
            btnTryAgain.setOnClickListener { viewModel.dispatch(Action.TrySignInAgain) }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        googleSignInViewModel.onResultFromActivity(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun render(state: State) {
        binding.state = state
        if (state.isSignInSuccess) {
            goToHome()
        }
    }

    private fun handleGoogleSignInResult(result: ProviderResult<GoogleSignInAccount>) {
        when (result) {
            is ProviderResult.Success -> viewModel.dispatch(
                Action.DoSignInProvider(
                    SignInProvider.Google,
                    result.value.id!!
                )
            )
            is ProviderResult.Error -> Timber.e(result.error)
            ProviderResult.Cancelled -> Timber.d("Google Sign In Cancelled")
        }
    }

    private fun onSignIn() {
        formViewModel.validate { email, password ->
            viewModel.dispatch(Action.DoSignIn(email, password))
        }
    }

    private fun onSignInUsingGoogle() {
        val account = GoogleSignIn.getLastSignedInAccount(context)
        when {
            account != null -> viewModel.dispatch(Action.DoSignInProvider(SignInProvider.Google, account.id!!))
            else -> googleSignInViewModel.signIn()
        }
    }

    private fun goToHome() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}