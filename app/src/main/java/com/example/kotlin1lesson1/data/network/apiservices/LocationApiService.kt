package com.example.kotlin1lesson1.data.network.apiservices

import com.example.kotlin1lesson1.data.network.dtos.RickAndMortyResponse
import com.example.kotlin1lesson1.data.network.dtos.episode.EpisodeModel
import com.example.kotlin1lesson1.data.network.dtos.location.LocationModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationApiService {

    @GET("location")
    suspend  fun fetchLocations(
        @Query("page") page: Int) : RickAndMortyResponse<LocationModel>

    @GET("location/{id}")
    fun fetchLocationApiService(@Path("id") id: Int) : Call<LocationModel>
}