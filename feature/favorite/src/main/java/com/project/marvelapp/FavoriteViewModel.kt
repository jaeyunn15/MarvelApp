package com.project.marvelapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.marvelapp.UiMapper.toEntity
import com.project.marvelapp.UiMapper.toUiModel
import com.project.marvelapp.common.CharacterUiModel
import com.project.marvelapp.usecase.AddFavoriteCharacterUseCase
import com.project.marvelapp.usecase.DeleteFavoriteCharacterUseCase
import com.project.marvelapp.usecase.GetFavoriteCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteCharacterUseCase: GetFavoriteCharacterUseCase,
    private val addFavoriteCharacterUseCase: AddFavoriteCharacterUseCase,
    private val deleteFavoriteCharacterUseCase: DeleteFavoriteCharacterUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow<FavoriteUiState>(FavoriteUiState.Wait)
    val viewState: StateFlow<FavoriteUiState> = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            getFavoriteCharacterUseCase()
                .onStart {
                    _viewState.update { FavoriteUiState.Loading }
                }
                .onEach { result ->
                    val data = result.map { it.toUiModel(true) }
                    _viewState.update {
                        FavoriteUiState.Success(data)
                    }
                }
                .catch { throwable ->
                    _viewState.update { FavoriteUiState.Error(throwable.message) }
                }
                .collect()
        }
    }

    fun addFavorite(model: CharacterUiModel) {
        addFavoriteCharacterUseCase(model.toEntity())
    }

    fun removeFavorite(model: CharacterUiModel) {
        deleteFavoriteCharacterUseCase(model.toEntity())
    }
}