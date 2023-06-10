package com.project.marvelapp.repository

import com.project.marvelapp.datasource.remote.CharacterDataSource
import com.project.marvelapp.entity.CharacterEntity
import com.project.marvelapp.mapper.CharacterMapper.toCharacterEntity
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterDataSource: CharacterDataSource
) : CharacterRepository {

    override suspend fun getCharacters(keyword: String, offset: Int): List<CharacterEntity> {
        val result = characterDataSource.getCharacters(
            keyword = keyword,
            limit = 10,
            offset = offset
        )

        return if (result.status == "Ok") {
            result.characterDataContainerResponse.results.map {
                it.toCharacterEntity()
            }
        } else {
           emptyList()
        }
    }

}