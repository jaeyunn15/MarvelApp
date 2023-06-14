package com.project.marvelapp

import com.project.marvelapp.state.SearchUiState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertIs

@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {

    @get:Rule
    val coroutineRule = TestCoroutinesRule()

    lateinit var searchViewModel: SearchViewModel

    @Before
    fun setUp() {
        searchViewModel = SearchViewModel()
    }

    @Test
    fun keywordStateIsInitiallyWait() = coroutineRule.runTest {
        assertEquals(SearchUiState.Wait, searchViewModel.searchKeyword.value)
    }

    @Test
    fun keywordStateIsSuccess_withKeywordChange() = coroutineRule.runTest {
        val collectJob = launch {
            searchViewModel.searchKeyword.collect()
        }

        searchViewModel.setSearchString("batman")

        val result = searchViewModel.searchKeyword.value
        assertIs<SearchUiState.Success<String>>(result)
        assertEquals(SearchUiState.Success("batman"), result)

        collectJob.cancel()
    }

    @Test
    fun keywordStateIsWait_withClearKeyword() = coroutineRule.runTest {
        val collectJob = launch {
            searchViewModel.searchKeyword.collect()
        }

        searchViewModel.clearSearchString()

        val result = searchViewModel.searchKeyword.value
        assertIs<SearchUiState.Wait>(result)

        collectJob.cancel()
    }
}