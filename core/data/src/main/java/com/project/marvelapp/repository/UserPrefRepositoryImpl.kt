package com.project.marvelapp.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.project.marvelapp.datasource.local.UserPrefDataSource
import com.project.marvelapp.datasource.local.UserPrefDataSourceImpl.Companion.CHARACTER_FAVORITE
import com.project.marvelapp.entity.CharacterEntity
import com.project.marvelapp.getSharedPreferenceFlow
import com.project.marvelapp.mapper.CharacterMapper.toCharacterEntity
import com.project.marvelapp.mapper.CharacterMapper.toCharacterVO
import com.project.marvelapp.model.vo.CharacterVO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class UserPrefRepositoryImpl @Inject constructor(
    private val userPrefDataSource: UserPrefDataSource
): UserPrefRepository {

    override fun getFavoriteCharacterFlow(): Flow<HashSet<CharacterEntity>> =
        userPrefDataSource.prefs.getSharedPreferenceFlow(CHARACTER_FAVORITE).map {
            userPrefDataSource.favoriteCharacterSets
        }.onStart {
            emit(hashSetOf())
        }.map {
            it.map {
                it.toCharacterEntity()
            }.toHashSet()
        }.conflate()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun addCharacter(character: CharacterEntity) {
        if (userPrefDataSource.favoriteCharacterSets.size >= 5) {
            userPrefDataSource.favoriteCharacterSets = userPrefDataSource.favoriteCharacterSets.apply {
                removeIf {
                    it.id == getOldCharacter().id
                }
                add(character.toCharacterVO())
            }
        } else {
            userPrefDataSource.favoriteCharacterSets = userPrefDataSource.favoriteCharacterSets.apply {
                add(character.toCharacterVO())
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun removeCharacter(character: CharacterEntity) {
        userPrefDataSource.favoriteCharacterSets = userPrefDataSource.favoriteCharacterSets.apply {
            removeIf {
                it.id == character.id
            }
        }
    }

    private fun getOldCharacter(): CharacterVO =
        userPrefDataSource.favoriteCharacterSets.sortedBy { it.savedTime }[0]
}