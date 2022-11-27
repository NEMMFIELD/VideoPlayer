package com.example.videoplayer.data.network

sealed class ApiState<out T> {
    // object Loading : ApiState()
    class Failure(val message: Throwable) : ApiState<Nothing>()
    class Success<T>(val data: T) : ApiState<T>()
    object Empty : ApiState<Nothing>()
}
