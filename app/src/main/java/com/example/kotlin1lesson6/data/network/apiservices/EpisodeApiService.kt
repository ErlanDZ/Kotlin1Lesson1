package com.example.kotlin1lesson6.data.network.apiservices

import com.example.kotlin1lesson6.data.network.dtos.RickAndMortyResponse
import com.example.kotlin1lesson6.data.network.dtos.episode.EpisodeModel
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