package com.example.kotlin1lesson6.data.network.pagingsource

import com.example.kotlin1lesson6.common.base.BasePagingSource
import com.example.kotlin1lesson6.data.network.apiservices.CharacterApiService
import com.example.kotlin1lesson6.data.network.dtos.character.CharacterModel

class CharacterPagingSource(
    private val service: CharacterApiService
) : BasePagingSource<CharacterModel>({ position ->
    service.fetchCharactersApiService(position)
})