package com.project.marvelapp

import com.project.marvelapp.entity.CharacterEntity
import com.project.marvelapp.repository.UserPrefRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TestUserRepository: UserPrefRepository {
    private val favoriteSet = hashSetOf<CharacterEntity>()

    override fun getFavoriteCharacterFlow(): Flow<HashSet<CharacterEntity>> {
        return flow {
            emit(favoriteSet)
        }
    }

    override fun addCharacter(character: CharacterEntity) {

    }

    override fun removeCharacter(character: CharacterEntity) {

    }

    fun addFavoriteSet(set: HashSet<CharacterEntity>) {
        favoriteSet.addAll(set)
    }
}