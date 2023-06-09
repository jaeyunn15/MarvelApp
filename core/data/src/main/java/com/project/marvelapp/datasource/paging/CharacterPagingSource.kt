package com.project.marvelapp.datasource.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.project.marvelapp.model.CharacterResponse
import com.project.marvelapp.datasource.remote.CharacterDataSource

class CharacterPagingSource(
    private val keyword: String,
    private val characterDataSource: CharacterDataSource
): PagingSource<Int, CharacterResponse>() {

    private var currentOffset = 0

    override fun getRefreshKey(state: PagingState<Int, CharacterResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResponse> {
        val page = params.key ?: STARTING_PAGE_INDEX

        return try {
            val result = characterDataSource.getCharacters(
                keyword = keyword,
                limit = 50,
                offset = currentOffset
            )

            currentOffset += result.characterDataContainerResponse.count

            val prefKey = if (page == STARTING_PAGE_INDEX) null else page - 1

            val nextKey = if (result.status == "Ok" && currentOffset < result.characterDataContainerResponse.total) {
                page + 1
            } else {
                null
            }

            LoadResult.Page(
                data = result.characterDataContainerResponse.results,
                prevKey = prefKey,
                nextKey = nextKey
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}