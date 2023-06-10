package com.project.marvelapp.api

import com.project.marvelapp.model.response.CharacterDataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("nameStartsWith") nameStartsWith: String?,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): CharacterDataWrapperResponse
}