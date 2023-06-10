package com.project.marvelapp.model.response

import com.google.gson.annotations.SerializedName

data class CharacterDataContainerResponse(
    @SerializedName("count")
    val count: Int,

    @SerializedName("limit")
    val limit: Int,

    @SerializedName("offset")
    val offset: Int,

    @SerializedName("results")
    val results: List<CharacterResponse>,

    @SerializedName("total")
    val total: Int
)