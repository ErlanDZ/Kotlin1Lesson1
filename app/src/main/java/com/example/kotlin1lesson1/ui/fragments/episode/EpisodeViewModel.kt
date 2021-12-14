package com.example.kotlin1lesson1.ui.fragments.episode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.kotlin1lesson1.common.base.BaseViewModel
import com.example.kotlin1lesson1.data.network.dtos.RickAndMortyResponse
import com.example.kotlin1lesson1.data.network.dtos.episode.EpisodeModel
import com.example.kotlin1lesson1.data.repositories.EpisodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private  val repository: EpisodeRepository
) : BaseViewModel() {

    var page = 1

    fun fetchLocationsViewModel() = repository.episodeRepository().cachedIn(viewModelScope)

}