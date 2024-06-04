package com.ramaa.pmobile_uas.domain.usecases.stocks

import androidx.paging.PagingData
import com.ramaa.pmobile_uas.data.remote.response.ResultsNewsItem
import com.ramaa.pmobile_uas.domain.repository.StockRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListNews @Inject constructor(
    private val stockRepository: StockRepository
) {
    operator fun invoke(): Flow<PagingData<ResultsNewsItem>> {
        return stockRepository.getNews()
    }
}