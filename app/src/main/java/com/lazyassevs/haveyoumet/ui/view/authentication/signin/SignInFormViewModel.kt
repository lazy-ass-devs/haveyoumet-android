package com.lazyassevs.haveyoumet.ui.view.authentication.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lazyassevs.haveyoumet.util.form.FormItem
import javax.inject.Inject

class SignInFormViewModel @Inject constructor() : ViewModel() {

    private val emailTrigger = MutableLiveData<FormItem<String>>()
    val email: LiveData<FormItem<String>> = emailTrigger

    private val passwordTrigger = MutableLiveData<FormItem<String>>()
    val password: LiveData<FormItem<String>> = passwordTrigger

    fun validate(action: (String, String) -> Unit) {
        val emailInput = email.value?.validate({ it.isNotEmpty() }, "Enter valid email")
        val passwordInput = password.value?.validate({ it.isNotEmpty() }, "Enter valid password")

        if (emailInput != null && passwordInput != null) {
            action(emailInput, passwordInput)
        }
    }

}