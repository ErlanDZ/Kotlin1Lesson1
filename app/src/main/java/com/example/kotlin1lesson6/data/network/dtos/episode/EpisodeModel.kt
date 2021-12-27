package com.example.kotlin1lesson6.data.network.dtos.episode

import com.example.kotlin1lesson6.common.base.IBaseDiffModel
import com.google.gson.annotations.SerializedName

data class EpisodeModel(
    @SerializedName("id") override val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("air_date") val air_date: String,
    @SerializedName("episode") val episode: String,
    @SerializedName("url") val url: String,
    @SerializedName("created") val created: String
) : IBaseDiffModel
