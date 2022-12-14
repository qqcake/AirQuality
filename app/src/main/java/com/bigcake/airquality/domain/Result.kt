package com.bigcake.airquality.domain

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Loading<T>(val data: T? = null) : Result<T>()
    data class Failure(val message: String) : Result<Nothing>()
}
