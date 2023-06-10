package com.project.marvelapp.repository

import com.project.marvelapp.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface UserPrefRepository {
    fun getFavoriteCharacterFlow(): Flow<HashSet<CharacterEntity>>
    fun addCharacter(character: CharacterEntity)
    fun removeCharacter(character: CharacterEntity)
}