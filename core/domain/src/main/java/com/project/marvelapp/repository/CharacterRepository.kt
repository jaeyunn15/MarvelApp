package com.project.marvelapp.repository

import com.project.marvelapp.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    var cacheList: MutableList<CharacterEntity>
    fun getCharacters(keyword: String, offset: Int): Flow<List<CharacterEntity>>
}