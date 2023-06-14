package com.project.marvelapp

import com.project.marvelapp.common.CharacterUiModel
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

val testFavoriteCharacterUiModelList = listOf<CharacterUiModel>(
    CharacterUiModel(id = 7, name = "batman", isFavorite = true),
    CharacterUiModel(id = 17, name = "spider", isFavorite = true),
    CharacterUiModel(id = 23, name = "antman", isFavorite = true),
    CharacterUiModel(id = 81, name = "hulk", isFavorite = true)
)

val testFavoriteCharacterUiModel = CharacterUiModel(id = 7, name = "batman", isFavorite = true)