package com.lazyassevs.domain.sealedclass

sealed class ProviderResult<out T> {

    data class Success<T>(val value: T) : ProviderResult<T>()

    data class Error(val error: Throwable) : ProviderResult<Nothing>()

    object Cancelled : ProviderResult<Nothing>()

}