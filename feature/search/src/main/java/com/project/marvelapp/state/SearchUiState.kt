package com.project.marvelapp.state

sealed class SearchUiState {
    object Wait : SearchUiState()

    object Loading : SearchUiState()

    data class Success(
        val characters: List<CharacterUiModel>
    ) : SearchUiState()

    data class Error(
        val msg: String? = null
    ) : SearchUiState()
}