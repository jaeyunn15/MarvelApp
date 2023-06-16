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
    fun `정상적으로 조회하는 경우, 성공적인 데이터를 반환`() = coroutineRule.runTest {
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
    fun `정상적으로 조회하는 경우, 성공적인 데이터를 반환하지만 빈 리스트인 경우`() = coroutineRule.runTest {
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
    fun `페이징 조회하는 경우, 기존 리스트에 추가되어 응답 결과가 반환`() = coroutineRule.runTest {
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
    fun `페이징 조회하는 경우, 기존 리스트 사이즈 대비 페이징 결과 리스트 사이즈가 증가한 응답 결과 반환`() = coroutineRule.runTest {
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