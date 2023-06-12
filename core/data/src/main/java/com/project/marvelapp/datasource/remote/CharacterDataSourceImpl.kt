package com.project.marvelapp.datasource.remote

import com.project.marvelapp.api.CharacterService
import com.project.marvelapp.di.DispatcherProvider
import com.project.marvelapp.model.response.CharacterDataWrapperResponse
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterDataSourceImpl @Inject constructor(
    private val characterService: CharacterService,
    private val dispatcher: DispatcherProvider
) : CharacterDataSource {

    override suspend fun getCharacters(
        keyword: String,
        limit: Int,
        offset: Int
    ): CharacterDataWrapperResponse {
        return withContext(dispatcher.io) {
            characterService.getCharacters(nameStartsWith = keyword, offset, limit)
        }
    }
}