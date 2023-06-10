package com.project.marvelapp.repository

import com.project.marvelapp.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacters(keyword: String, offset: Int): List<CharacterEntity>
}