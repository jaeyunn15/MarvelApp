package com.project.marvelapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.marvelapp.common.CharacterUiModel
import com.project.marvelapp.entity.CharacterEntity
import com.project.marvelapp.usecase.AddFavoriteCharacterUseCase
import com.project.marvelapp.usecase.DeleteFavoriteCharacterUseCase
import com.project.marvelapp.usecase.GetFavoriteCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    getFavoriteCharacterUseCase: GetFavoriteCharacterUseCase,
    private val addFavoriteCharacterUseCase: AddFavoriteCharacterUseCase,
    private val deleteFavoriteCharacterUseCase: DeleteFavoriteCharacterUseCase
) : ViewModel() {

    val uiState: StateFlow<FavoriteUiState> = getFavoriteCharacterUseCase()
        .onStart { FavoriteUiState.Loading }
        .mapLatest { result ->
            FavoriteUiState.Success(result.map(CharacterEntity::toUiModel))
        }
        .catch { throwable ->
            FavoriteUiState.Error(throwable.message)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = FavoriteUiState.Wait
        )

    fun addFavorite(model: CharacterUiModel) = addFavoriteCharacterUseCase(model.toEntity())

    fun removeFavorite(model: CharacterUiModel) = deleteFavoriteCharacterUseCase(model.toEntity())
}