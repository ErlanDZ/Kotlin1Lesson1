package com.example.kotlin1lesson1.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin1lesson1.common.resource.Resource
import com.example.kotlin1lesson1.presentation.state.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


abstract class BaseViewModel : ViewModel() {
    protected fun <T> MutableStateFlow<UIState<T>>.subscribeTo(
        request: () -> Flow<Resource<T>>
    ){
        viewModelScope.launch(Dispatchers.IO) {
            request().collectLatest{
                when(it){
                    is Resource.Loading ->{
                        this@subscribeTo.value = UIState.Loading()
                    }
                    is Resource.Error -> it.massage?. let { error ->
                        this@subscribeTo.value = UIState.Error(error)
                    }
                    is Resource.Success -> it.data?. let { data ->
                        this@subscribeTo.value = UIState.Success(data)
                    }
                }
            }
        }
    }
}