package com.project.marvelapp

import androidx.paging.PagingData
import com.project.marvelapp.entity.CharacterEntity

sealed interface SearchUiState {
    object Loading : SearchUiState

    data class Success(
        val characters: PagingData<CharacterEntity>
    ) : SearchUiState

}