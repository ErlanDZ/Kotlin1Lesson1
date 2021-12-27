package com.example.kotlin1lesson6.data.network.dtos.character

import com.example.kotlin1lesson6.common.base.IBaseDiffModel
import com.google.gson.annotations.SerializedName

data class CharacterModel(
    @SerializedName("id") override val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("species" )val species: String,
    @SerializedName("type") val type: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("image") val image: String
    ) : IBaseDiffModel
