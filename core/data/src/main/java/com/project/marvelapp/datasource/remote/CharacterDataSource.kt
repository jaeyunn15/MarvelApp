package com.project.marvelapp.datasource.remote

import com.project.marvelapp.model.response.CharacterDataWrapperResponse


interface CharacterDataSource {
    suspend fun getCharacters(keyword: String, limit: Int, offset: Int): CharacterDataWrapperResponse
}