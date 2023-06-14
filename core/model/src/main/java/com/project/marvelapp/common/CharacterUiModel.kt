package com.project.marvelapp.common

data class CharacterUiModel(
    val id: Int = -1,
    val name: String = "",
    val description: String  = "",
    val modified: String = "",
    val thumbnail: String = "",
    val isFavorite: Boolean = false
)