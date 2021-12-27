package com.example.kotlin1lesson6.data.network.dtos

import com.google.gson.annotations.SerializedName

data class RickAndMortyResponse<T>(
    @SerializedName("info")
    val info: Info,

    @SerializedName("results")
    val results: List<T>
)

