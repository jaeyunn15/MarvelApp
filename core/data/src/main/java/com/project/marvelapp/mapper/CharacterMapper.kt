package com.project.marvelapp.mapper

import com.project.marvelapp.entity.CharacterEntity
import com.project.marvelapp.model.CharacterResponse

object CharacterMapper {

    fun CharacterResponse.toCharacterEntity(): CharacterEntity {
        return CharacterEntity(
            id = id,
            name = name,
            description = description,
            modified = modified,
            thumbnail = thumbnail.path + "." + thumbnail.extension
        )
    }
}