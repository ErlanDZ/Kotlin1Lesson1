package com.example.kotlin1lesson1.ui.fragments.location

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.kotlin1lesson1.common.base.BaseViewModel
import com.example.kotlin1lesson1.data.repositories.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val repository: LocationRepository
) : BaseViewModel() {

    fun fetchLocations() = repository.locationRepository().cachedIn(viewModelScope)
}