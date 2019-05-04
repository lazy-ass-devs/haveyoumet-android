package com.lazyassevs.haveyoumet.util.databinding.form

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField

data class FormItem<T>(
    val input: ObservableField<T> = ObservableField(),
    val errMsg: ObservableString = ObservableString(),
    val hasError: ObservableBoolean = ObservableBoolean()
) {

    fun validate(validator: (T) -> Boolean): T? {
        val newData = input.get()?.let { if (validator(it)) it else null }
        hasError.set(newData == null)
        return newData
    }

    fun validate(validator: (T) -> Boolean, errMsg: String): T? {
        val newData = input.get()?.let { if (validator(it)) it else null }
        if (newData != null) {
            this.hasError.set(false)
            this.errMsg.set(null)
        } else {
            this.hasError.set(true)
            this.errMsg.set(errMsg)
        }

        return newData
    }

}