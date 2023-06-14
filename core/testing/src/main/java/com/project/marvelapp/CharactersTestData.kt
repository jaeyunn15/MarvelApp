package com.project.marvelapp

import com.project.marvelapp.entity.CharacterEntity

val testCharactersData = listOf<CharacterEntity>(
    CharacterEntity(id = 1, name = "xxx1"),
    CharacterEntity(id = 2, name = "xxx2"),
    CharacterEntity(id = 3, name = "xxx3")
)


val testPagingCharactersData = listOf<CharacterEntity>(
    CharacterEntity(id = 4, name = "xxx1"),
    CharacterEntity(id = 5, name = "xxx2"),
    CharacterEntity(id = 3, name = "xxx3")
)
