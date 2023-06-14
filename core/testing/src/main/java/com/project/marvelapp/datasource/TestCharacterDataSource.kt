package com.project.marvelapp.datasource

import com.project.marvelapp.datasource.remote.CharacterDataSource
import com.project.marvelapp.model.response.CharacterDataContainerResponse
import com.project.marvelapp.model.response.CharacterDataWrapperResponse
import com.project.marvelapp.model.response.CharacterResponse

class TestCharacterDataSource : CharacterDataSource {

    private val characterList = mutableListOf<CharacterResponse>()

    private var isSuccess = true

    private fun getSuccessCharacterDataWrapperResponse(characterList: List<CharacterResponse>) =
        CharacterDataWrapperResponse(
            code = 200,
            status = "Ok",
            characterDataContainerResponse = CharacterDataContainerResponse(
                count = 10,
                limit = 10,
                total = 20,
                offset = 10,
                results = characterList
            )
        )

    private fun getErrorCharacterDataWrapperResponse(characterList: List<CharacterResponse>) =
        CharacterDataWrapperResponse(
            code = 200,
            status = "",
            characterDataContainerResponse = CharacterDataContainerResponse(
                count = 10,
                limit = 10,
                total = 20,
                offset = 10,
                results = characterList
            )
        )


    override suspend fun getCharacters(
        keyword: String,
        limit: Int,
        offset: Int
    ): CharacterDataWrapperResponse {
        return if (isSuccess) {
            getSuccessCharacterDataWrapperResponse(characterList)
        } else {
            getErrorCharacterDataWrapperResponse(characterList)
        }
    }

    fun setSuccessFlag(flag: Boolean) {
        isSuccess = flag
    }

    fun addResponseList(list: List<CharacterResponse>) {
        characterList.addAll(list)
    }

    fun clearResponseList() {
        characterList.clear()
    }
}