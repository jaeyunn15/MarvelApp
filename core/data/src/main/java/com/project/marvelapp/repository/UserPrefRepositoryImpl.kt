package com.project.marvelapp.repository

import android.os.Build
import androidx.annotation.RequiresApi
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
        //todo 5개 체크해서 넘으면 추가 안됨.
        userPrefDataSource.favoriteCharacterSets = userPrefDataSource.favoriteCharacterSets.apply {
            add(character.toCharacterVO())
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

}