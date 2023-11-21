package com.ramaa.narutowiki.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ramaa.narutowiki.domain.model.Character

class CharacterPagingSource(
    private val narutoAPI: NarutoAPI
) : PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: 1
        return try {
            val characterResponse = narutoAPI.getCharacters(page = page)
            totalNewsCount += characterResponse.characters.size
            val articles = characterResponse.characters.distinctBy { it.id }

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