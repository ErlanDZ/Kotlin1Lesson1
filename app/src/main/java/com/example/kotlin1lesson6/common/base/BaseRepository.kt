package com.example.kotlin1lesson6.common.base

import com.example.kotlin1lesson6.common.resource.Resource
import kotlinx.coroutines.flow.flow
import java.lang.Exception

abstract class BaseRepository {
    protected fun <T> doRequest(request: suspend () -> T) = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = request()))
        } catch (ioException: Exception){
            emit(
                Resource.Error(
                    data = null, massage = ioException.localizedMessage ?: "Error Occurred"
                )
            )
        }
    }
}