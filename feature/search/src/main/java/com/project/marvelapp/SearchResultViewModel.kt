package com.project.marvelapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.marvelapp.common.CharacterUiModel
import com.project.marvelapp.entity.CharacterEntity
import com.project.marvelapp.mapper.UiMapper.toEntity
import com.project.marvelapp.mapper.UiMapper.toUiModel
import com.project.marvelapp.state.SearchUiState
import com.project.marvelapp.usecase.AddFavoriteCharacterUseCase
import com.project.marvelapp.usecase.DeleteFavoriteCharacterUseCase
import com.project.marvelapp.usecase.GetCharactersUseCase
import com.project.marvelapp.usecase.GetFavoriteCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getFavoriteCharacterUseCase: GetFavoriteCharacterUseCase,
    private val addFavoriteCharacterUseCase: AddFavoriteCharacterUseCase,
    private val deleteFavoriteCharacterUseCase: DeleteFavoriteCharacterUseCase
) : ViewModel() {
    private var offset = 0
    private val _keyword = MutableStateFlow("")

    private val _viewState = MutableStateFlow<SearchUiState>(SearchUiState.Wait)
    val viewState: StateFlow<SearchUiState> = _viewState.asStateFlow()

    private val _characterList = MutableStateFlow<List<CharacterEntity>?>(null)

    init {
        collectResultAsUiModel()
        collectKeywordState()
    }

    private fun collectKeywordState() = viewModelScope.launch {
        _keyword
            .filter { it.isNotBlank() }
            .debounce(300) //0.3초 딜레이
            .mapLatest { keyword -> // 새로운 플로우 방출 시, 이전 계산 로직 취소 처리
                if (keyword.length >= 2) {
                    _viewState.update { SearchUiState.Loading }
                    offset = 0
                    fetchResults(keyword, offset)
                } else if (keyword.isNotEmpty()) {
                    _viewState.update { SearchUiState.Error("최소 2글자 입력 해야 합니다.") }
                } else {
                    _viewState.update { SearchUiState.Wait }
                }
            }.catch { throwable ->
                _viewState.update { SearchUiState.Error(throwable.toString()) }
            }
            .collect()
    }

    private fun collectResultAsUiModel() = viewModelScope.launch {
        _characterList.filterNotNull()
            .combine(getFavoriteCharacterUseCase()) { result, favoriteSet ->
                result.map { it.toUiModel(favoriteSet.contains(it)) }
            }.map { list ->
            _viewState.update { SearchUiState.Success(list, loadMoreProgress = false) }
        }.collect()
    }

    fun onLoadMoreCharacters() {
        viewModelScope.launch {
            _viewState.update {
                SearchUiState.Success(
                    characters = (it as SearchUiState.Success).characters,
                    loadMoreProgress = true
                )
            }
            offset += 10
            fetchResults(_keyword.value, offset)
        }
    }

    private fun fetchResults(keyword: String, offset: Int) = viewModelScope.launch {
        getCharactersUseCase(keyword, offset)
            .onEach { result ->
                if (result.size == _characterList.value?.size) {
                    _viewState.update {
                        SearchUiState.Success(
                            characters = (it as SearchUiState.Success).characters,
                            loadMoreProgress = false
                        )
                    }
                } else {
                    _characterList.update { result.toList() }
                }
            }.collect()
    }

    fun updateKeyword(keyword: String) {
        _keyword.update { keyword }
    }

    fun addFavorite(model: CharacterUiModel) {
        addFavoriteCharacterUseCase(model.toEntity())
    }

    fun removeFavorite(model: CharacterUiModel) {
        deleteFavoriteCharacterUseCase(model.toEntity())
    }
}