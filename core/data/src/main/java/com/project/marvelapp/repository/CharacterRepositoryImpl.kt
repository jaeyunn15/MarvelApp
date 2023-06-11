package com.project.marvelapp.repository

import com.project.marvelapp.datasource.remote.CharacterDataSource
import com.project.marvelapp.entity.CharacterEntity
import com.project.marvelapp.mapper.CharacterMapper.toCharacterEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterDataSource: CharacterDataSource
) : CharacterRepository {

    private var isPaginationAvailable = true

    override var cacheList: MutableList<CharacterEntity> = mutableListOf()

    override suspend fun getCharacters(keyword: String, offset: Int): Flow<List<CharacterEntity>> = flow {
        if (offset == 0) {
            cacheList.clear()
            isPaginationAvailable = true
        }

        if (isPaginationAvailable) {
            val result = characterDataSource.getCharacters(
                keyword = keyword,
                limit = 10,
                offset = offset
            )

            if (offset >= result.characterDataContainerResponse.total) {
                isPaginationAvailable = false
            }

            if (result.status == "Ok") {
                result.characterDataContainerResponse.results.map {
                    cacheList.add(it.toCharacterEntity())
                }
                emit(cacheList)
            }
        }
    }
}