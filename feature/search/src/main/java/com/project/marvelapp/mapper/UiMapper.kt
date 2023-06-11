package com.project.marvelapp.mapper

import com.project.marvelapp.CharacterUiModel
import com.project.marvelapp.entity.CharacterEntity

object UiMapper {

    fun CharacterEntity.toUiModel(isFavorite: Boolean): CharacterUiModel {
        return CharacterUiModel(
            id, name, description, modified, thumbnail,
            isFavorite = isFavorite
        )
    }

    fun CharacterUiModel.toEntity(): CharacterEntity {
        return CharacterEntity(id, name, description, modified, thumbnail)
    }
}