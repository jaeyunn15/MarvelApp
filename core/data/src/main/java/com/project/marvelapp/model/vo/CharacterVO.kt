package com.project.marvelapp.model.vo

data class CharacterVO(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: String,
    val savedTime: Long
)