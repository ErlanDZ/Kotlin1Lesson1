package com.example.kotlin1lesson1.common.base

import androidx.lifecycle.liveData
import com.example.kotlin1lesson1.common.resource.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

abstract class BaseRepository {
    protected fun <T> doRequest(request: suspend () -> T) = liveData(Dispatchers.IO) {
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