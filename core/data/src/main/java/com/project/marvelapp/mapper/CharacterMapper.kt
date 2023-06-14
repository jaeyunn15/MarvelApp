package com.project.marvelapp.mapper

import com.project.marvelapp.entity.CharacterEntity
import com.project.marvelapp.model.response.CharacterResponse
import com.project.marvelapp.model.vo.CharacterVO

fun CharacterResponse.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        description = description,
        modified = modified,
        thumbnail = thumbnail.path + "." + thumbnail.extension
    )
}

fun CharacterVO.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        description = description,
        modified = modified,
        thumbnail = thumbnail
    )
}

fun CharacterEntity.toCharacterVO(): CharacterVO {
    return CharacterVO(
        id = id,
        name = name,
        description = description,
        modified = modified,
        thumbnail = thumbnail,
        savedTime = System.currentTimeMillis()
    )
}