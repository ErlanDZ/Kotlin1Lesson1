package com.example.kotlin1lesson1.ui.fragments.character.detail

import com.example.kotlin1lesson1.common.base.BaseViewModel
import com.example.kotlin1lesson1.data.network.dtos.character.CharacterModel
import com.example.kotlin1lesson1.data.repositories.CharacterRepository
import com.example.kotlin1lesson1.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: CharacterRepository
) : BaseViewModel(){
    private val _characterState = MutableStateFlow<UIState<CharacterModel>>(UIState.Loading())
    val characterState: StateFlow<UIState<CharacterModel>> = _characterState



      fun  fetchCharacter(id: Int) {
          _characterState.subscribeTo {
              repository.characterRepository(id)
          }
      }
}