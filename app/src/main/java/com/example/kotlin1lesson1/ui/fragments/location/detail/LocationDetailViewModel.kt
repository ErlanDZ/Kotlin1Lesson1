package com.example.kotlin1lesson1.ui.fragments.location.detail

import com.example.kotlin1lesson1.common.base.BaseViewModel
import com.example.kotlin1lesson1.data.network.dtos.location.LocationModel
import com.example.kotlin1lesson1.data.repositories.LocationRepository
import com.example.kotlin1lesson1.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(
    private val repository: LocationRepository
) : BaseViewModel() {

    private val _locationState = MutableStateFlow<UIState<LocationModel>>(UIState.Loading())
    val locationState: StateFlow<UIState<LocationModel>> = _locationState


    fun fetchLocation(id: Int) {
        _locationState.subscribeTo {
            repository.locationRepository(id)
        }
    }
}