package com.lazyassevs.haveyoumet.ui.authentication.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lazyassevs.haveyoumet.R
import com.lazyassevs.haveyoumet.databinding.FragmentSignInBinding
import com.lazyassevs.haveyoumet.util.base.BaseFragment
import com.lazyassevs.haveyoumet.util.extensions.observe
import com.lazyassevs.haveyoumet.util.extensions.withBinding
import com.lazyassevs.haveyoumet.util.extensions.withViewModel

class SignInFragment : BaseFragment() {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var viewModel: SignInViewModel
    private lateinit var formViewModel: SignInFormViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = withBinding(inflater, R.layout.fragment_sign_in, container)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = withViewModel(this, viewModelFactory) {
            observe(observableState, ::render)
        }
        formViewModel = withViewModel(this, viewModelFactory)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.form = formViewModel
        binding.apply {
            btnSignIn.setOnClickListener { onSignIn() }
            btnTryAgain.setOnClickListener { viewModel.dispatch(Action.TrySignInAgain) }
        }
    }

    private fun onSignIn() {
        formViewModel.validate { email, password ->
            viewModel.dispatch(Action.DoSignIn(email, password))
        }
    }

    private fun render(state: State) {
        binding.state = state
        if (state.isSignInSuccess) {
            goToHome()
        }
    }

    private fun goToHome() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}