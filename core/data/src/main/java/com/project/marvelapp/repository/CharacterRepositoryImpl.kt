package com.project.marvelapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.project.marvelapp.datasource.paging.CharacterPagingSource
import com.project.marvelapp.datasource.remote.CharacterDataSource
import com.project.marvelapp.di.IoDispatcher
import com.project.marvelapp.entity.CharacterEntity
import com.project.marvelapp.mapper.CharacterMapper.toCharacterEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterDataSource: CharacterDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : CharacterRepository {

    override fun getCharacters(keyword: String): Flow<PagingData<CharacterEntity>> {
        return Pager(
            PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CharacterPagingSource(keyword, characterDataSource)
            }
        ).flow.map {
            it.map {
                it.toCharacterEntity()
            }
        }.flowOn(ioDispatcher)
    }

}