package com.project.marvelapp

import com.project.marvelapp.entity.CharacterEntity

sealed class SearchUiState {
    object Wait : SearchUiState()

    object Loading : SearchUiState()

    data class Success(
        val characters: List<CharacterEntity>
    ) : SearchUiState()

    data class Error(
        val msg: String? = null
    ) : SearchUiState()
}