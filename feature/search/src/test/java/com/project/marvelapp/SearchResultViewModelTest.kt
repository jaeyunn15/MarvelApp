package com.project.marvelapp

import com.project.marvelapp.state.SearchResultUiState
import com.project.marvelapp.usecase.AddFavoriteCharacterUseCase
import com.project.marvelapp.usecase.DeleteFavoriteCharacterUseCase
import com.project.marvelapp.usecase.GetCharactersUseCase
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runCurrent
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class SearchResultViewModelTest {

    @get:Rule
    val coroutineRule = TestCoroutinesRule()

    private val getCharactersUseCase = mockk<GetCharactersUseCase>()
    private val addFavoriteCharacterUseCase = mockk<AddFavoriteCharacterUseCase>()
    private val deleteFavoriteCharacterUseCase = mockk<DeleteFavoriteCharacterUseCase>()

    lateinit var searchResultViewModel: SearchResultViewModel

    @Before
    fun setUp() {
        searchResultViewModel = SearchResultViewModel(
            getCharactersUseCase = getCharactersUseCase,
            addFavoriteCharacterUseCase = addFavoriteCharacterUseCase,
            deleteFavoriteCharacterUseCase = deleteFavoriteCharacterUseCase
        )
    }

    @Test
    fun stateIsInitiallyLoading() = coroutineRule.runTest {
        assertEquals(SearchResultUiState.Wait, searchResultViewModel.uiState.value)
    }

    @Test
    fun stateIsEmptyQuery_withOneWordSearchQuery() = coroutineRule.runTest {
        val collectJob = launch {
            searchResultViewModel.uiState.collect()
        }

        searchResultViewModel.updateKeyword("a")

        runCurrent()

        assertEquals(SearchResultUiState.EmptyQuery, searchResultViewModel.uiState.value)

        collectJob.cancel()
    }
}