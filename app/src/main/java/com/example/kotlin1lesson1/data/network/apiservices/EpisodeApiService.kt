package com.example.kotlin1lesson1.data.network.apiservices

import com.example.kotlin1lesson1.data.network.dtos.RickAndMortyResponse
import com.example.kotlin1lesson1.data.network.dtos.character.CharacterModel
import com.example.kotlin1lesson1.data.network.dtos.episode.EpisodeModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodeApiService {

    @GET("episode")
    suspend fun episodeApiService(
        @Query("page") page: Int) : RickAndMortyResponse<EpisodeModel>

    @GET("episode/{id}")
    suspend fun fetchEpisodeApiService(@Path("id") id: Int) : EpisodeModel
}