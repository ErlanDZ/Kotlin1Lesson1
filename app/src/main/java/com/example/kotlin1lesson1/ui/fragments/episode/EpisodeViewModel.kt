package com.example.kotlin1lesson1.ui.fragments.episode

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.kotlin1lesson1.common.base.BaseViewModel
import com.example.kotlin1lesson1.data.repositories.EpisodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@InternalCoroutinesApi
@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val repository: EpisodeRepository
) : BaseViewModel() {

    var page = 1

    fun fetchLocations() = repository.episodeRepository().cachedIn(viewModelScope)

}