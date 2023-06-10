package com.project.marvelapp.repository

import com.project.marvelapp.datasource.local.UserPrefDataSource
import com.project.marvelapp.datasource.local.UserPrefDataSourceImpl.Companion.CHARACTER_FAVORITE
import com.project.marvelapp.entity.CharacterEntity
import com.project.marvelapp.getSharedPreferenceFlow
import com.project.marvelapp.mapper.CharacterMapper.toCharacterEntity
import com.project.marvelapp.mapper.CharacterMapper.toCharacterVO
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

    override fun addCharacter(character: CharacterEntity) {
        userPrefDataSource.favoriteCharacterSets = userPrefDataSource.favoriteCharacterSets.apply {
            add(character.toCharacterVO())
        }
    }

    override fun removeCharacter(character: CharacterEntity) {
        TODO("Not yet implemented")
    }

}