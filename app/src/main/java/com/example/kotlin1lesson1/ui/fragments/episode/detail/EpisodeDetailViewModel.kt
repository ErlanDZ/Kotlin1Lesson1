package com.example.kotlin1lesson1.ui.fragments.episode.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin1lesson1.data.network.dtos.character.CharacterModel
import com.example.kotlin1lesson1.data.network.dtos.episode.EpisodeModel
import com.example.kotlin1lesson1.data.repositories.CharacterRepository
import com.example.kotlin1lesson1.data.repositories.EpisodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(
    private val repository: EpisodeRepository
) : ViewModel() {


    fun  fetchEpisode() = repository.episodeRepository(1)
}