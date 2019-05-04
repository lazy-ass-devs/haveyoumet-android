package com.lazyassevs.haveyoumet.util.form

data class FormItem<T>(
    var input: T? = null,
    var errMsg: String? = null
) {

    fun validate(validator: (T) -> Boolean): T? = input?.let { if (validator(it)) it else null }

    fun validate(validator: (T) -> Boolean, errMsg: String): T? {
        val newData = input?.let { if (validator(it)) it else null }
        when {
            newData != null -> this.errMsg = null
            else -> this.errMsg = errMsg
        }

        return newData
    }

}