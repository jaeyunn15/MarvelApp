package com.project.marvelapp.state

sealed class UiState<out T> {
    object Wait : UiState<Nothing>()

    data class Loading<T>(val model: T?) : UiState<T>()

    data class Success<T>(val model: T) : UiState<T>()

    data class Error<T>(val model: T?) : UiState<T>()

    fun getOrNull() = when (this) {
        is Loading -> model
        is Success -> model
        is Error -> model
        else -> null
    }
}