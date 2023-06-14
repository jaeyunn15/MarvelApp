package com.project.marvelapp.repository

import com.project.marvelapp.entity.CharacterEntity
import com.project.marvelapp.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TestCharacterRepository: CharacterRepository {

    private val testCharactersData: MutableList<CharacterEntity> = mutableListOf()

    override fun getCharacters(keyword: String, offset: Int): Flow<List<CharacterEntity>> = flow {
        emit(testCharactersData)
    }

    fun addCharacters(data: List<CharacterEntity>) {
        testCharactersData.addAll(data)
    }
}