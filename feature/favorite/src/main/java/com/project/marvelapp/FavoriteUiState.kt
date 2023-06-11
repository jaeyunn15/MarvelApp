package com.project.marvelapp

import com.project.marvelapp.common.CharacterUiModel

sealed class FavoriteUiState {
    object Wait : FavoriteUiState()

    object Loading : FavoriteUiState()

    data class Success(
        val characters: List<CharacterUiModel>
    ) : FavoriteUiState()

    data class Error(
        val msg: String? = null
    ) : FavoriteUiState()
}