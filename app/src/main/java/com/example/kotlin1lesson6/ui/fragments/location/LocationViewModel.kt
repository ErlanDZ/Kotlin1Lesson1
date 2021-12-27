package com.example.kotlin1lesson6.ui.fragments.location

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.kotlin1lesson6.common.base.BaseViewModel
import com.example.kotlin1lesson6.data.repositories.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class LocationViewModel (
    private val repository: LocationRepository
) : BaseViewModel() {

    fun fetchLocations() = repository.locationRepository().cachedIn(viewModelScope)
}