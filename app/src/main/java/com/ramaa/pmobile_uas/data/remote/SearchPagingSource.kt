package com.ramaa.pmobile_uas.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ramaa.pmobile_uas.data.remote.response.CompanyResponse
import com.ramaa.pmobile_uas.util.Constants
import java.lang.Exception

class SearchPagingSource(
    private val api: StockAPI,
    private val searchQuery: String
) : PagingSource<Int, CompanyResponse>() {

    override fun getRefreshKey(state: PagingState<Int, CompanyResponse>): Int? {
        return state.anchorPosition?.let { anchorPage ->
            val page = state.closestPageToPosition(anchorPage)
            page?.nextKey?.minus(1) ?: page?.prevKey?.plus(1)
        }
    }

    private var totalCharactersCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CompanyResponse> {
        val page = params.key ?: 1
        return try {
            val characterResponse = api.searchCompany(symbol = searchQuery, apiKey = Constants.API_KEY)
            totalCharactersCount += 1

            Log.d("SearchPagingSource", "Data loaded successfully for page: $page")

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