package com.ramaa.pmobile_uas.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ramaa.pmobile_uas.data.remote.response.ResultsCompanies
import com.ramaa.pmobile_uas.domain.model.ItemCharacter
import com.ramaa.pmobile_uas.util.Constants
import java.lang.Exception

class SearchPagingSource(
    private val api: NarutoAPI,
    private val searchQuery: String
) : PagingSource<Int, ResultsCompanies>() {

    override fun getRefreshKey(state: PagingState<Int, ResultsCompanies>): Int? {
        return state.anchorPosition?.let { anchorPage ->
            val page = state.closestPageToPosition(anchorPage)
            page?.nextKey?.minus(1) ?: page?.prevKey?.plus(1)
        }
    }

    private var totalCharactersCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultsCompanies> {
        val page = params.key ?: 1
        return try {
            val characterResponse = api.searchCharacter(symbol = searchQuery, apiKey = Constants.API_KEY)
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