package com.project.marvelapp.model

import com.google.gson.annotations.SerializedName
import com.project.marvelapp.model.CharacterDataContainerResponse

data class CharacterDataWrapperResponse(
    @SerializedName("code")
    val code: Int,

    @SerializedName("status")
    val status: String,

    @SerializedName("data")
    val characterDataContainerResponse: CharacterDataContainerResponse
)