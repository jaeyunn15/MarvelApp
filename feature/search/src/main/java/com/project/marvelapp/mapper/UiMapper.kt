package com.project.marvelapp.mapper

import com.project.marvelapp.entity.CharacterEntity

object UiMapper {

    fun CharacterEntity.toUiModel(): com.project.marvelapp.common.CharacterUiModel {
        return com.project.marvelapp.common.CharacterUiModel(
            id, name, description, modified, thumbnail,
            isFavorite = isFavorite
        )
    }

    fun com.project.marvelapp.common.CharacterUiModel.toEntity(): CharacterEntity {
        return CharacterEntity(id, name, description, modified, thumbnail)
    }
}