package com.project.marvelapp.datasource.remote

import com.project.marvelapp.api.CharacterService
import com.project.marvelapp.di.IoDispatcher
import com.project.marvelapp.model.CharacterDataWrapperResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterDataSourceImpl @Inject constructor(
    private val characterService: CharacterService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : CharacterDataSource {

    override suspend fun getCharacters(
        keyword: String,
        limit: Int,
        offset: Int
    ): CharacterDataWrapperResponse {
        return withContext(ioDispatcher) {
            characterService.getCharacters(nameStartsWith = keyword, offset, limit)
        }
    }
}