package com.project.marvelapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.marvelapp.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
): ViewModel() {
    private var offset = 0
    private val _keyword = MutableStateFlow("")

    private val _viewState = MutableStateFlow<SearchUiState>(SearchUiState.Wait)
    val viewState: StateFlow<SearchUiState> = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            _keyword
                .filter { it.isNotBlank() }
                .debounce(300) //0.3초 딜레이
                .mapLatest {// 새로운 플로우 방출 시, 이전 계산 로직 취소 처리
                    if (it.length >= 2) {
                        _viewState.update { SearchUiState.Loading }
                        offset = 0
                        val result = getCharactersUseCase(
                            keyword = it,
                            offset = offset
                        )
                        _viewState.update { SearchUiState.Success(result) }
                    } else if (it.isNotEmpty()) {
                        _viewState.update { SearchUiState.Error("최소 2글자 입력해야 합니다.") }
                    } else {
                        _viewState.update { SearchUiState.Wait }
                    }
                }
                .catch { throwable ->
                    _viewState.update { SearchUiState.Error(throwable.toString()) }
                }
                .collect()
        }
    }

    fun onLoadMoreCharacters() {
        viewModelScope.launch {
            offset += 10
            val result = getCharactersUseCase(
                keyword = _keyword.value,
                offset = offset
            )
            _viewState.update {
                val datas = (it as SearchUiState.Success).characters
                it.copy(characters = datas + result)
            }
        }
    }

    fun updateKeyword(keyword: String) {
        _keyword.update { keyword }
    }
}