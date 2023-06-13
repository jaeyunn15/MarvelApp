package com.project.marvelapp.state

sealed class SearchUiState<out T> {
    object Wait : SearchUiState<Nothing>()

    data class Loading<T>(val model: T?) : SearchUiState<T>()

    data class Success<T>(val model: T) : SearchUiState<T>()

    data class Error<T>(val model: T?) : SearchUiState<T>()

    fun getOrNull() = when (this) {
        is Loading -> model
        is Success -> model
        is Error -> model
        else -> null
    }
}