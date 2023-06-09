package com.project.marvelapp.model

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("description")
    val description: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("modified")
    val modified: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("thumbnail")
    val thumbnail: ThumbnailResponse
)

data class ThumbnailResponse(
    @SerializedName("path")
    val path: String,

    @SerializedName("extension")
    val extension: String
)