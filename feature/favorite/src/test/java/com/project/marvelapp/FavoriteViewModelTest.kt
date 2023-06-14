package com.project.marvelapp

import com.project.marvelapp.repository.TestUserRepository
import com.project.marvelapp.rule.TestCoroutinesRule
import com.project.marvelapp.usecase.AddFavoriteCharacterUseCase
import com.project.marvelapp.usecase.DeleteFavoriteCharacterUseCase
import com.project.marvelapp.usecase.GetFavoriteCharacterUseCase
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
class FavoriteViewModelTest {

    @get:Rule
    val coroutineRule = TestCoroutinesRule()

    lateinit var favoriteViewModel: FavoriteViewModel

    private val userTestRepository = TestUserRepository()

    private val getFavoriteCharacterUseCase = GetFavoriteCharacterUseCase(
        userPrefRepository = userTestRepository
    )

    private val addFavoriteCharacterUseCase = AddFavoriteCharacterUseCase(
        userPrefRepository = userTestRepository
    )

    private val deleteFavoriteCharacterUseCase = DeleteFavoriteCharacterUseCase(
        userPrefRepository = userTestRepository
    )

    @Before
    fun setUp() {
        favoriteViewModel = FavoriteViewModel(
            getFavoriteCharacterUseCase = getFavoriteCharacterUseCase,
            addFavoriteCharacterUseCase = addFavoriteCharacterUseCase,
            deleteFavoriteCharacterUseCase = deleteFavoriteCharacterUseCase
        )
    }

    @Test
    fun stateIsInitiallyWait() = coroutineRule.runTest {
        assertEquals(FavoriteUiState.Wait, favoriteViewModel.uiState.value)
    }

    @Test
    fun stateIsSuccess_withEmptyFavoriteSet() = coroutineRule.runTest {
        val collectJob = launch {
            favoriteViewModel.uiState.collect()
        }

        runCurrent()

        assertIs<FavoriteUiState.Success>(favoriteViewModel.uiState.value)
        assertEquals(0, (favoriteViewModel.uiState.value as FavoriteUiState.Success).characters.size)

        collectJob.cancel()
    }

    @Test
    fun stateIsSuccess_withOneFavoriteSet() = coroutineRule.runTest {
        val collectJob = launch {
            favoriteViewModel.uiState.collect()
        }

        testFavoriteCharacterUiModelList.forEach {
            favoriteViewModel.addFavorite(it)
        }

        runCurrent()

        assertIs<FavoriteUiState.Success>(favoriteViewModel.uiState.value)
        assertEquals(4, (favoriteViewModel.uiState.value as FavoriteUiState.Success).characters.size)

        collectJob.cancel()
    }

    @Test
    fun stateIsSuccess_withRemoveOneFavoriteSet() = coroutineRule.runTest {
        //초기 데이터 4개 삽입
        testFavoriteCharacterUiModelList.forEach {
            favoriteViewModel.addFavorite(it)
        }

        val collectJob = launch { favoriteViewModel.uiState.collect() }

        favoriteViewModel.removeFavorite(testFavoriteCharacterUiModel)

        runCurrent()

        //1개 삭제 후, 3개 확인
        val afterRemoveState = favoriteViewModel.uiState.value
        assertIs<FavoriteUiState.Success>(afterRemoveState)
        assertEquals(3, afterRemoveState.characters.size)

        collectJob.cancel()
    }
}