package com.project.marvelapp.usecase

import com.project.marvelapp.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor (
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke(keyword: String, offset: Int) = characterRepository.getCharacters(keyword, offset)
}