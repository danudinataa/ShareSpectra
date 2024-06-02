package com.ramaa.pmobile_uas.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ramaa.pmobile_uas.data.remote.response.NewsResponse
import com.ramaa.pmobile_uas.data.remote.response.ResultsNewsItem
import com.ramaa.pmobile_uas.data.remote.response.ResultsStockItem
import com.ramaa.pmobile_uas.domain.model.ItemCharacter
import com.ramaa.pmobile_uas.util.Constants

class NewsPagingSource(
    private val narutoAPI: NarutoAPI
) : PagingSource<Int, ResultsNewsItem>() {

    override fun getRefreshKey(state: PagingState<Int, ResultsNewsItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultsNewsItem> {
        val page = params.key ?: 1
        val pageSize = params.loadSize

        return try {
            val newsResponse = narutoAPI.getNews(page = page, apiKey = Constants.API_KEY)
            val articles = newsResponse.data?.results
                ?.filterNotNull()
                ?.distinctBy { it.url } ?: emptyList()

            // Logging untuk debug
            Log.d("NewsPagingSource", "Page: $page, Articles: ${articles.size}, PageSize: $pageSize")

            val nextKey = if (articles.size > pageSize) null else page + 1

            Log.d("Next Key", "Key: $nextKey, Articles: ${articles.size}, PageSize: $pageSize")
            LoadResult.Page(
                data = articles,
                nextKey = nextKey,
                prevKey = if (page == 1) null else page - 1
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }
}
