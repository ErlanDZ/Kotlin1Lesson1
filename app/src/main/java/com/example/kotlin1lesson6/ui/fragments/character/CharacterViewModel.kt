package com.example.kotlin1lesson6.ui.fragments.character

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.kotlin1lesson6.common.base.BaseViewModel
import com.example.kotlin1lesson6.data.repositories.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject



class CharacterViewModel (
    private val repository: CharacterRepository
) : BaseViewModel() {

    fun fetchCharacters() = repository.charactersRepository().cachedIn(viewModelScope)
}