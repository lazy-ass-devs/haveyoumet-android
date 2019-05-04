package com.lazyassevs.haveyoumet.util.databinding

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

object EditTextBindingAdapter {

    @JvmStatic
    @BindingAdapter("errMsg")
    fun setError(textInputLayout: TextInputLayout, error: String?) {
        textInputLayout.isErrorEnabled = !error.isNullOrEmpty()
        textInputLayout.error = when {
            error.isNullOrEmpty() -> null
            else -> error
        }
    }

}