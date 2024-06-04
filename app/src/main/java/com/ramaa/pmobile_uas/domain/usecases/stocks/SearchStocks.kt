package com.ramaa.pmobile_uas.domain.usecases.stocks

import androidx.paging.PagingData
import com.ramaa.pmobile_uas.data.remote.response.CompanyResponse
import com.ramaa.pmobile_uas.domain.repository.StockRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchStocks @Inject constructor(
    private val stockRepository: StockRepository
) {
    operator fun invoke(searchQuery: String): Flow<PagingData<CompanyResponse>> {
        return stockRepository.searchCompany(
            searchQuery = searchQuery
        )
    }
}