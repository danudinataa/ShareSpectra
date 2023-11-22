package com.ramaa.narutowiki.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ramaa.narutowiki.domain.model.ItemCharacter
import java.lang.Exception

class SearchPagingSource(
    private val api: NarutoAPI,
    private val searchQuery: String
) : PagingSource<Int, ItemCharacter>() {

    override fun getRefreshKey(state: PagingState<Int, ItemCharacter>): Int? {
        return state.anchorPosition?.let { anchorPage ->
            val page = state.closestPageToPosition(anchorPage)
            page?.nextKey?.minus(1) ?: page?.prevKey?.plus(1)
        }
    }

    private var totalCharactersCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemCharacter> {
        val page = params.key ?: 1
        return try {
            val characterResponse = api.searchCharacter(searchQuery = searchQuery, page = page)
            totalCharactersCount += 1

            LoadResult.Page(
                data = listOf(characterResponse) ,
                nextKey = if (totalCharactersCount == 1) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }
}