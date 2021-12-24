package com.example.kotlin1lesson1.ui.fragments.episode.detail

import com.example.kotlin1lesson1.common.base.BaseViewModel
import com.example.kotlin1lesson1.data.network.dtos.episode.EpisodeModel
import com.example.kotlin1lesson1.data.repositories.EpisodeRepository
import com.example.kotlin1lesson1.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(
    private val repository: EpisodeRepository
) : BaseViewModel() {

    private val _episodeState = MutableStateFlow<UIState<EpisodeModel>>(UIState.Loading())
    val episodeState: StateFlow<UIState<EpisodeModel>> = _episodeState


    fun fetchEpisode(id: Int) {
        _episodeState.subscribeTo {
            repository.episodeRepository(id)
        }
    }
}