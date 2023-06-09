package com.project.marvelapp.repository

import androidx.paging.PagingData
import com.project.marvelapp.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacters(keyword: String): Flow<PagingData<CharacterEntity>>
}