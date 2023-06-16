package com.project.marvelapp

import com.project.marvelapp.entity.CharacterEntity

fun CharacterEntity.toFavoriteUiModel(): com.project.marvelapp.common.CharacterUiModel {
    return com.project.marvelapp.common.CharacterUiModel(id, name, description, modified, thumbnail, true)
}

fun com.project.marvelapp.common.CharacterUiModel.toEntity(): CharacterEntity {
    return CharacterEntity(id, name, description, modified, thumbnail)
}