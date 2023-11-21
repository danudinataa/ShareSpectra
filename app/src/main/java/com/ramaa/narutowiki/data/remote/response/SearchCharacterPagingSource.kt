package com.ramaa.narutowiki.data.remote.response

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ramaa.narutowiki.data.remote.NarutoAPI
import com.ramaa.narutowiki.domain.model.Character
import java.lang.Exception

class SearchCharacterPagingSource(
    private val api: NarutoAPI,
    private val searchQuery: String,
    private val sources: String
) : PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPage ->
            val page = state.closestPageToPosition(anchorPage)
            page?.nextKey?.minus(1) ?: page?.prevKey?.plus(1)
        }
    }

    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: 1
        return try {
            val characterResponse = api.searchCharacter(searchQuery = searchQuery, sources = sources, page = page)
            totalNewsCount += characterResponse.characters.size
            val articles = characterResponse.characters.distinctBy { it.id }

            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewsCount == characterResponse.total) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }


}