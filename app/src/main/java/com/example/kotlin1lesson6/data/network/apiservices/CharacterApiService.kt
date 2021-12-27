package com.example.kotlin1lesson6.data.network.apiservices

import com.example.kotlin1lesson6.data.network.dtos.RickAndMortyResponse
import com.example.kotlin1lesson6.data.network.dtos.character.CharacterModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApiService {

    @GET("character")
      suspend fun fetchCharactersApiService(@Query("page") page: Int
    ) : RickAndMortyResponse<CharacterModel>

    @GET("character/{id}")
    suspend fun fetchCharacterApiService(@Path("id") id: Int) : CharacterModel
}