package com.project.marvelapp.model.response

import com.google.gson.annotations.SerializedName

data class CharacterDataWrapperResponse(
    @SerializedName("code")
    val code: Int,

    @SerializedName("status")
    val status: String,

    @SerializedName("data")
    val characterDataContainerResponse: CharacterDataContainerResponse
)