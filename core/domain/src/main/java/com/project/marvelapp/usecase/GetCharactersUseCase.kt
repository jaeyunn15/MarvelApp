package com.project.marvelapp.usecase

import com.project.marvelapp.entity.CharacterEntity
import com.project.marvelapp.repository.CharacterRepository
import com.project.marvelapp.repository.UserPrefRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val userPrefRepository: UserPrefRepository
) {
    operator fun invoke(keyword: String, offset: Int) =
        characterRepository.getCharacters(keyword, offset)
            .mapToUserSearchResult(userPrefRepository.getFavoriteCharacterFlow())
}
private fun Flow<List<CharacterEntity>>.mapToUserSearchResult(userDataStream: Flow<HashSet<CharacterEntity>>): Flow<List<CharacterEntity>> =
    combine(userDataStream) { searchResult, userData ->
        searchResult.map {
            it.copy(isFavorite = userData.contains(it))
        }
    }