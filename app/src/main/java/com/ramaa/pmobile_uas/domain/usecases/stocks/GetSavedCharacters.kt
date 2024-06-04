package com.ramaa.pmobile_uas.domain.usecases.stocks

import com.ramaa.pmobile_uas.data.local.StocksDao
import com.ramaa.pmobile_uas.data.remote.response.ResultsStockItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedCharacters @Inject constructor(
    private val stocksDao: StocksDao
) {

    operator fun invoke(): Flow<List<ResultsStockItem>> {
        return stocksDao.getStocks()
    }
}