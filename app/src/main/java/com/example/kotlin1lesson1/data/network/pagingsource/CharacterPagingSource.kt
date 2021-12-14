package com.example.kotlin1lesson1.data.network.pagingsource

import com.example.kotlin1lesson1.common.base.BasePagingSource
import com.example.kotlin1lesson1.data.network.apiservices.CharacterApiService
import com.example.kotlin1lesson1.data.network.dtos.character.CharacterModel

class CharacterPagingSource(
    private val service: CharacterApiService
) : BasePagingSource<CharacterModel>({ position ->
    service.fetchCharactersApiService(position)
})