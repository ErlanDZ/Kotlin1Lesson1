package com.example.kotlin1lesson1.ui.fragments.location.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin1lesson1.data.network.dtos.episode.EpisodeModel
import com.example.kotlin1lesson1.data.network.dtos.location.LocationModel
import com.example.kotlin1lesson1.data.repositories.EpisodeRepository
import com.example.kotlin1lesson1.data.repositories.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(
    private val repository: LocationRepository
) : ViewModel() {

    fun  fetchLocation(id: Int): MutableLiveData<LocationModel> {
        return repository.locationRepository(id)
    }
}