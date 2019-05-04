package com.lazyassevs.haveyoumet.ui.authentication.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lazyassevs.haveyoumet.util.form.FormItem
import javax.inject.Inject

class RegisterFormViewModel @Inject constructor() : ViewModel() {

    private val emailTrigger = MutableLiveData<FormItem<String>>()
    val email: LiveData<FormItem<String>> = emailTrigger

    private val passwordTrigger = MutableLiveData<FormItem<String>>()
    val password: LiveData<FormItem<String>> = passwordTrigger

    private val repasswordTrigger = MutableLiveData<FormItem<String>>()
    val repassword: LiveData<FormItem<String>> = repasswordTrigger

}