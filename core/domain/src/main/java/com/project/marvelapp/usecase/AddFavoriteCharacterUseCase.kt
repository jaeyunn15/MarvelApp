package com.project.marvelapp.usecase

import com.project.marvelapp.entity.CharacterEntity
import com.project.marvelapp.repository.UserPrefRepository
import javax.inject.Inject

class AddFavoriteCharacterUseCase @Inject constructor (
    private val userPrefRepository: UserPrefRepository
) {
    operator fun invoke(model: CharacterEntity) {
        userPrefRepository.addCharacter(model)
    }
}