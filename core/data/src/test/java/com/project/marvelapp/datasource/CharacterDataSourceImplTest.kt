package com.project.marvelapp.datasource

import com.project.marvelapp.FakeResponse
import com.project.marvelapp.dispatcher.TestCoroutinesRule
import com.project.marvelapp.dispatcher.TestDispatcherProvider
import com.project.marvelapp.api.CharacterService
import com.project.marvelapp.datasource.remote.CharacterDataSource
import com.project.marvelapp.datasource.remote.CharacterDataSourceImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class CharacterDataSourceImplTest {

    @get:Rule
    val coroutineRule = TestCoroutinesRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = TestDispatcherProvider(coroutineRule.testDispatcher)

    private val characterService = mockk<CharacterService>()

    private lateinit var characterDataSource: CharacterDataSource

    @Before
    fun setUp() {
        characterDataSource = CharacterDataSourceImpl(
            characterService,
            testDispatcher
        )
    }

    @Test
    fun `검색어 입력 시, 정상적으로 데이터 응답`() = coroutineRule.runTest {

        coEvery {
            characterService.getCharacters(
                nameStartsWith = "batman",
                offset = 0,
                limit = 10
            )
        } returns FakeResponse.fakeSuccessResponse

        assertEquals(
            characterDataSource.getCharacters(
                "batman",
                offset = 0,
                limit = 10
            ),
            FakeResponse.fakeSuccessResponse
        )
    }

    @Test
    fun `검색어 입력 시, 일치하는 데이터 없을 시 빈 리스트 응답`() = coroutineRule.runTest {

        coEvery {
            characterService.getCharacters(
                nameStartsWith = "batata",
                offset = 0,
                limit = 10
            )
        } returns FakeResponse.fakeEmptyResponse

        assertEquals(
            characterDataSource.getCharacters(
                "batata",
                offset = 0,
                limit = 10
            ),
            FakeResponse.fakeEmptyResponse
        )
    }
}