package com.project.marvelapp.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.project.marvelapp.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TestUserRepository: UserPrefRepository {
    private var favoriteSet = linkedSetOf<CharacterEntity>()

    override fun getFavoriteCharacterFlow(): Flow<LinkedHashSet<CharacterEntity>> {
        return flow {
            emit(favoriteSet)
        }
    }

    override fun addCharacter(character: CharacterEntity) {
        favoriteSet.add(character)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun removeCharacter(character: CharacterEntity) {
        favoriteSet.apply {
            removeIf {
                it.id == character.id
            }
        }
    }

    fun addFavoriteSet(set: HashSet<CharacterEntity>) {
        favoriteSet.addAll(set)
    }
}