package com.project.marvelapp

import com.project.marvelapp.state.SearchResultUiState
import com.project.marvelapp.usecase.AddFavoriteCharacterUseCase
import com.project.marvelapp.usecase.DeleteFavoriteCharacterUseCase
import com.project.marvelapp.usecase.GetCharactersUseCase
import io.mockk.mockk
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
class SearchResultViewModelTest {

    @get:Rule
    val coroutineRule = TestCoroutinesRule()

    private val characterTestRepository = TestCharacterRepository()
    private val userTestRepository = TestUserRepository()

    private val getCharactersUseCase = GetCharactersUseCase(
        characterRepository = characterTestRepository,
        userPrefRepository = userTestRepository
    )
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

    @Test
    fun stateIsSuccess_withMatchingQuery() = coroutineRule.runTest {
        val collectJob = launch {
            searchResultViewModel.uiState.collect()
        }

        searchResultViewModel.updateKeyword("xxx")

        characterTestRepository.addCharacters(testCharactersData) //반환될 테스트 데이터 추가

        userTestRepository.addFavoriteSet(hashSetOf()) //좋아요 정보 없는 상태로 추가

        delay(SearchResultViewModel.REQUEST_DELAY_TIME)

        runCurrent()

        val result = searchResultViewModel.uiState.value

        assertIs<SearchResultUiState.Success>(result)

        assertEquals(3, result.characters.size)

        collectJob.cancel()
    }

    @Test
    fun stateIsSuccess_withEmptyMatchingQuery() = coroutineRule.runTest {
        val collectJob = launch {
            searchResultViewModel.uiState.collect()
        }

        searchResultViewModel.updateKeyword("abc")

        characterTestRepository.addCharacters(emptyList()) //반환 될 빈 데이터 추가

        userTestRepository.addFavoriteSet(hashSetOf()) //좋아요 정보 없는 상태로 추가

        delay(SearchResultViewModel.REQUEST_DELAY_TIME)

        runCurrent()

        val result = searchResultViewModel.uiState.value

        assertIs<SearchResultUiState.Success>(result)

        assertEquals(0, result.characters.size)

        collectJob.cancel()
    }
}