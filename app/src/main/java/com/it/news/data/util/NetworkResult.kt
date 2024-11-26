package com.it.news.data.util

sealed class NetworkResult<out T>(val data: T? = null, val message: String? = null) {
    class Loading<T> : NetworkResult<T>()
    data class Success<T>(val _data: T) : NetworkResult<T>(data = _data)
    data class Failure<T>(val _message: String) : NetworkResult<T>(message = _message)
}