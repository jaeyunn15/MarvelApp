package com.project.marvelapp.usecase

import androidx.paging.PagingData
import com.project.marvelapp.entity.CharacterEntity
import com.project.marvelapp.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor (
    private val characterRepository: CharacterRepository
) {

    operator fun invoke(keyword: String): Flow<PagingData<CharacterEntity>> {
        return characterRepository.getCharacters(keyword)
    }
}