package com.project.marvelapp.usecase

import com.project.marvelapp.entity.CharacterEntity
import com.project.marvelapp.repository.UserPrefRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteCharacterUseCase @Inject constructor(
    private val userPrefRepository: UserPrefRepository
) {
    operator fun invoke() : Flow<HashSet<CharacterEntity>> {
        return userPrefRepository.getFavoriteCharacterFlow()
    }
}