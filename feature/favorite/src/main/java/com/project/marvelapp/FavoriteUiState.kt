package com.project.marvelapp

import com.project.marvelapp.common.CharacterUiModel

sealed interface FavoriteUiState {
    object Wait : FavoriteUiState

    object Loading : FavoriteUiState

    data class Success(
        val characters: List<CharacterUiModel>
    ) : FavoriteUiState

    data class Error(
        val msg: String? = null
    ) : FavoriteUiState
}