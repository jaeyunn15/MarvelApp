package com.project.marvelapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.project.marvelapp.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    getCharactersUseCase: GetCharactersUseCase
): ViewModel() {

    private val _keyword = MutableStateFlow("")

    val homeUiState = _keyword
        .debounce(3000)
        .flatMapLatest {
            if (it.length >= 2) {
                getCharactersUseCase(it)
            } else {
                emptyFlow()
            }
        }.cachedIn(viewModelScope)

    fun updateKeyword(keyword: String) {
        _keyword.update { keyword }
    }
}