package com.example.kotlin1lesson1.data.network.dtos

import com.google.gson.annotations.SerializedName
import java.util.*

data class RickAndMortyResponse<T>(
    @SerializedName("info")
    val info: Info,

    @SerializedName("results")
    val results: List<T>
)

