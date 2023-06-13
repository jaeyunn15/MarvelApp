package com.project.marvelapp

import androidx.lifecycle.ViewModel
import com.project.marvelapp.state.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {

    private var _searchKeyword: MutableStateFlow<SearchUiState<String>> =
        MutableStateFlow(SearchUiState.Wait)
    val searchKeyword = _searchKeyword.asStateFlow()

    fun setSearchString(value: String) {
        _searchKeyword.update {
            SearchUiState.Success(value)
        }
    }

    fun clearSearchString() {
        _searchKeyword.update { SearchUiState.Wait }
    }

}