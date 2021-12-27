package com.example.kotlin1lesson6.ui.fragments.episode

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.kotlin1lesson6.common.base.BaseViewModel
import com.example.kotlin1lesson6.data.repositories.EpisodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject


class EpisodeViewModel (
    private val repository: EpisodeRepository
) : BaseViewModel() {

    var page = 1

    fun fetchLocations() = repository.episodeRepository().cachedIn(viewModelScope)

}