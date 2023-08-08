package com.example.favouritegitrepo.data.remote

sealed class NetworkResult<T>(
    val data : T? = null,
    val message: String? = null
){
    class SUCCESS<T>(data : T): NetworkResult<T>(data)

    class FAILURE<T>(message: String?, data: T? = null) : NetworkResult<T>(data,message)

    class LOADING<T> : NetworkResult<T>()

    class DEFAULT<T> : NetworkResult<T>()
}
