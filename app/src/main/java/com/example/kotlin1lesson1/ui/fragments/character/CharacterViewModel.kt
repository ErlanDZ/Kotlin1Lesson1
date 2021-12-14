package com.example.kotlin1lesson1.ui.fragments.character

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.kotlin1lesson1.common.base.BaseViewModel
import com.example.kotlin1lesson1.data.repositories.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharacterRepository
) : BaseViewModel   () {

    var page = 1
    fun fetchCharacters() = repository.charactersRepository().cachedIn(viewModelScope)
}