package com.project.marvelapp.state

sealed class SearchResultUiState {
    object Wait : SearchResultUiState()

    object Loading : SearchResultUiState()

    object EmptyQuery : SearchResultUiState()

    data class LoadFailed(
        val errorMsg: String
    ) : SearchResultUiState()

    data class Success(
        val characters: List<com.project.marvelapp.common.CharacterUiModel>,
        val isPaging: Boolean = false
    ) : SearchResultUiState() {
        fun isEmpty(): Boolean = characters.isEmpty()
    }
}

data class SearchState(
    val searchQuery: String,
    val offset: Int = 0
)