package com.project.marvelapp.viewmodel

import com.project.marvelapp.SearchViewModel
import com.project.marvelapp.TestCoroutinesRule
import com.project.marvelapp.state.SearchUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runCurrent
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
    fun `아무 이벤트가 없을 경우, 초기 상태는 Wait`() = coroutineRule.runTest {
        assertEquals(SearchUiState.Wait, searchViewModel.searchKeyword.value)
    }

    @Test
    fun `키워드가 변경되었을 때, 변경 사항에 대해서 Success 상태와 올바른 키워드가 전달됨`() = coroutineRule.runTest {
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
    fun `현재 Success(batman) 상태에서, 키워드가 초기화 되었을 때, 상태는 Wait으로 변경됨`() = coroutineRule.runTest {
        searchViewModel.setSearchString("batman") //기본 설정을 batman으로 두어 Success로 바꿔 둠

        val collectJob = launch {
            searchViewModel.searchKeyword.collect()
        }

        searchViewModel.clearSearchString()

        val result = searchViewModel.searchKeyword.value
        assertIs<SearchUiState.Wait>(result)

        collectJob.cancel()
    }
}