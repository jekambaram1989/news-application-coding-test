package com.it.news.data.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

fun <T> resultFlow(call: suspend () -> Response<T>) = flow {
    emit(NetworkResult.Loading())
    val c = call()
    try {
        if (c.isSuccessful) {
            emit(NetworkResult.Success(c.body()!!))
        } else {
            emit(NetworkResult.Failure("Data not available"))
        }
    } catch (e: Exception) {
        emit(NetworkResult.Failure("Data not available"))
    }
}.flowOn(Dispatchers.IO)