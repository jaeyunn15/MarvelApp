package com.project.marvelapp

import com.project.marvelapp.common.CharacterUiModel
import com.project.marvelapp.entity.CharacterEntity
import com.project.marvelapp.model.response.CharacterDataContainerResponse
import com.project.marvelapp.model.response.CharacterDataWrapperResponse
import com.project.marvelapp.model.response.CharacterResponse
import com.project.marvelapp.model.response.ThumbnailResponse
import com.project.marvelapp.model.vo.CharacterVO

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

val fakeSuccessCharacterResponse = listOf(
    CharacterResponse(
        id = 1,
        thumbnail = ThumbnailResponse(
            path = "",
            extension = ""
        ),
        description = "",
        modified = "",
        name = "batman"
    ),
    CharacterResponse(
        id = 2,
        thumbnail = ThumbnailResponse(
            path = "",
            extension = ""
        ),
        description = "",
        modified = "",
        name = "ironMan"
    ),
    CharacterResponse(
        id = 3,
        thumbnail = ThumbnailResponse(
            path = "",
            extension = ""
        ),
        description = "",
        modified = "",
        name = "spiderMan"
    ),
    CharacterResponse(
        id = 4,
        thumbnail = ThumbnailResponse(
            path = "",
            extension = ""
        ),
        description = "",
        modified = "",
        name = "spiderMan"
    ),
    CharacterResponse(
        id = 5,
        thumbnail = ThumbnailResponse(
            path = "",
            extension = ""
        ),
        description = "",
        modified = "",
        name = "spiderMan"
    ),
    CharacterResponse(
        id = 6,
        thumbnail = ThumbnailResponse(
            path = "",
            extension = ""
        ),
        description = "",
        modified = "",
        name = "spiderMan"
    ),
    CharacterResponse(
        id = 7,
        thumbnail = ThumbnailResponse(
            path = "",
            extension = ""
        ),
        description = "",
        modified = "",
        name = "spiderMan"
    ),
    CharacterResponse(
        id = 8,
        thumbnail = ThumbnailResponse(
            path = "",
            extension = ""
        ),
        description = "",
        modified = "",
        name = "spiderMan"
    ),
    CharacterResponse(
        id = 9,
        thumbnail = ThumbnailResponse(
            path = "",
            extension = ""
        ),
        description = "",
        modified = "",
        name = "spiderMan"
    ),
    CharacterResponse(
        id = 10,
        thumbnail = ThumbnailResponse(
            path = "",
            extension = ""
        ),
        description = "",
        modified = "",
        name = "spiderMan"
    ),
)

val testFavoriteCharacterVoSet = hashSetOf<CharacterVO>(
    CharacterVO(
        id = 1,
        name = "batman",
        description = "mask on",
        modified = "",
        thumbnail = "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg",
        savedTime = 5L
    ),
    CharacterVO(
        id = 2,
        name = "spider",
        description = "whip",
        modified = "",
        thumbnail = "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg",
        savedTime = 3L
    ),
    CharacterVO(
        id = 3,
        name = "ironman",
        description = "steel",
        modified = "",
        thumbnail = "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg",
        savedTime = 4L
    )
)