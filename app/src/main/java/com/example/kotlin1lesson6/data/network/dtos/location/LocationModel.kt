package com.example.kotlin1lesson6.data.network.dtos.location

import com.example.kotlin1lesson6.common.base.IBaseDiffModel
import com.google.gson.annotations.SerializedName

data class LocationModel(
    @SerializedName("id") override val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("dimension") val dimension: String,
    @SerializedName("url") val url: String,
    @SerializedName("created") val created: String
) : IBaseDiffModel
