package com.project.marvelapp

import com.project.marvelapp.model.response.CharacterDataContainerResponse
import com.project.marvelapp.model.response.CharacterDataWrapperResponse
import com.project.marvelapp.model.response.CharacterResponse
import com.project.marvelapp.model.response.ThumbnailResponse

object FakeResponse {

    val fakeSuccessResponse = CharacterDataWrapperResponse(
        code = 200,
        status = "success",
        characterDataContainerResponse = CharacterDataContainerResponse(
            count = 10,
            limit = 10,
            total = 20,
            results = listOf(
                CharacterResponse(
                    id = 123,
                    thumbnail = ThumbnailResponse(
                        path = "",
                        extension = ""
                    ),
                    description = "",
                    modified = "",
                    name = "batman"
                )
            ),
            offset = 10
        )
    )

    val fakeEmptyResponse = CharacterDataWrapperResponse(
        code = 200,
        status = "success",
        characterDataContainerResponse = CharacterDataContainerResponse(
            count = 0,
            limit = 10,
            total = 0,
            results = listOf(),
            offset = 0
        )
    )
}