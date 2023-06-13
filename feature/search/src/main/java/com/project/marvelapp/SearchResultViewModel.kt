package com.project.marvelapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.marvelapp.common.CharacterUiModel
import com.project.marvelapp.common.Result
import com.project.marvelapp.common.asResult
import com.project.marvelapp.mapper.UiMapper.toEntity
import com.project.marvelapp.mapper.UiMapper.toUiModel
import com.project.marvelapp.state.SearchResultUiState
import com.project.marvelapp.state.SearchState
import com.project.marvelapp.usecase.AddFavoriteCharacterUseCase
import com.project.marvelapp.usecase.DeleteFavoriteCharacterUseCase
import com.project.marvelapp.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val addFavoriteCharacterUseCase: AddFavoriteCharacterUseCase,
    private val deleteFavoriteCharacterUseCase: DeleteFavoriteCharacterUseCase
) : ViewModel() {
    private val _searchParamState = MutableStateFlow(SearchState(""))
    private var _cachedList = listOf<CharacterUiModel>()

    val uiState = _searchParamState
        .filter { it.searchQuery.isNotBlank() }
        .flatMapLatest { searchParamState ->
            if (searchParamState.searchQuery.length < 2) {
                flowOf(SearchResultUiState.EmptyQuery)
            } else {
                if (searchParamState.offset == 0) {
                    delay(300)
                }
                handleResult(searchParamState)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SearchResultUiState.Wait,
        )

    private fun handleResult(state: SearchState) =
        getCharactersUseCase(state.searchQuery, state.offset)
            .asResult()
            .map {
                when (it) {
                    is Result.Success -> {
                        val result = it.data.map { it.toUiModel() }.apply {
                            _cachedList = this
                        }
                        SearchResultUiState.Success(
                            characters = result,
                            isPaging = false
                        )
                    }

                    is Result.Error -> {
                        SearchResultUiState.LoadFailed(it.exception?.message.orEmpty())
                    }

                    Result.Loading -> {
                        if (state.offset == 0) {
                            SearchResultUiState.Loading
                        } else {
                            SearchResultUiState.Success(
                                characters = _cachedList,
                                isPaging = true
                            )
                        }
                    }
                }
            }

    fun onLoadMoreCharacters() = _searchParamState.update {
        it.copy(offset = it.offset + 10)
    }

    fun updateKeyword(keyword: String) = _searchParamState.update {
        it.copy(searchQuery = keyword, offset = 0)
    }

    fun addFavorite(model: CharacterUiModel) = addFavoriteCharacterUseCase(model.toEntity())
    fun removeFavorite(model: CharacterUiModel) = deleteFavoriteCharacterUseCase(model.toEntity())
}