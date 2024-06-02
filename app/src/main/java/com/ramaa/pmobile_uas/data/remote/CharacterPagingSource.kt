package com.ramaa.pmobile_uas.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ramaa.pmobile_uas.data.remote.response.ResultsStockItem
import com.ramaa.pmobile_uas.domain.model.ItemCharacter
import com.ramaa.pmobile_uas.util.Constants

class CharacterPagingSource(
    private val narutoAPI: NarutoAPI
) : PagingSource<Int, ResultsStockItem>() {

    override fun getRefreshKey(state: PagingState<Int, ResultsStockItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultsStockItem> {
        val page = params.key ?: 1
        val pageSize = params.loadSize

        return try {
            val stockResponse = narutoAPI.getCharacters(page = page, apiKey = Constants.API_KEY)
            val articles = stockResponse.data?.results
                ?.filterNotNull()
                ?.distinctBy { it.symbol } ?: emptyList()
            totalNewsCount += articles.size

            LoadResult.Page(
                data = articles,
                nextKey = if (articles.size < pageSize) null else page + 1,
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