package com.ramaa.narutowiki.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ramaa.narutowiki.domain.model.ItemCharacter

class CharacterPagingSource(
    private val narutoAPI: NarutoAPI
) : PagingSource<Int, ItemCharacter>() {

    override fun getRefreshKey(state: PagingState<Int, ItemCharacter>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemCharacter> {
        val page = params.key ?: 1
        return try {
            val characterResponse = narutoAPI.getCharacters(page = page)
            totalNewsCount += characterResponse.itemCharacters.size
            val articles = characterResponse.itemCharacters.distinctBy { it.id }

            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewsCount == characterResponse.total) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }
}