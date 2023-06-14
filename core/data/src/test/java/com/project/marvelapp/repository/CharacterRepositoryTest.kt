package com.project.marvelapp.repository

import com.project.marvelapp.datasource.TestCharacterDataSource
import com.project.marvelapp.dispatcher.TestCoroutinesRule
import com.project.marvelapp.fakeSuccessCharacterResponse
import kotlinx.coroutines.flow.first
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class CharacterRepositoryTest {

    @get:Rule
    val coroutineRule = TestCoroutinesRule()

    private val characterDataSource = TestCharacterDataSource()

    lateinit var characterRepository: CharacterRepository

    @Before
    fun setUp() {
        characterRepository = CharacterRepositoryImpl(
            characterDataSource = characterDataSource
        )
    }

    @Test
    fun sameResultSize_withSuccessRequest() = coroutineRule.runTest {
        characterDataSource.setSuccessFlag(true)
        characterDataSource.addResponseList(fakeSuccessCharacterResponse)

        val result = characterRepository.getCharacters(keyword = "data", offset = 0).first()
        val expect = 10

        assertEquals(
            expect,
            result.size
        )
    }

    @Test
    fun successButEmptySize_withNoRespondingRequest() = coroutineRule.runTest {
        characterDataSource.setSuccessFlag(true)
        characterDataSource.addResponseList(emptyList())

        val result = characterRepository.getCharacters(keyword = "data", offset = 0).first()
        val expect = 0

        assertEquals(
            expect,
            result.size
        )
    }

    @Test
    fun original10Data_pagination10Data() = coroutineRule.runTest {
        characterDataSource.setSuccessFlag(true)
        characterDataSource.addResponseList(fakeSuccessCharacterResponse)

        val result = characterRepository.getCharacters(keyword = "data", offset = 0).first()
        val expect = 10

        assertEquals(
            expect,
            result.size
        )

        val pagingResult = characterRepository.getCharacters(keyword = "data", offset = 10).first()
        val pagingExpect = 20

        assertEquals(
            pagingExpect,
            pagingResult.size
        )
    }

    @Test
    fun original10Data_pagination2Data() = coroutineRule.runTest {
        characterDataSource.setSuccessFlag(true)
        characterDataSource.addResponseList(fakeSuccessCharacterResponse)

        val result = characterRepository.getCharacters(keyword = "data", offset = 0).first()
        val expect = 10

        assertEquals(
            expect,
            result.size
        )

        characterDataSource.clearResponseList()
        characterDataSource.addResponseList(fakeSuccessCharacterResponse.take(2))

        val pagingResult = characterRepository.getCharacters(keyword = "data", offset = 10).first()
        val pagingExpect = 12

        assertEquals(
            pagingExpect,
            pagingResult.size
        )
    }
}