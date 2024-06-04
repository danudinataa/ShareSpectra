package com.ramaa.pmobile_uas.domain.usecases.stocks

import com.ramaa.pmobile_uas.data.local.StocksDao
import com.ramaa.pmobile_uas.data.remote.response.ResultsStockItem
import javax.inject.Inject

class GetSavedStock @Inject constructor(
    private val stocksDao: StocksDao
) {

    suspend operator fun invoke(symbol: String): ResultsStockItem? {
        return stocksDao.getStock(symbol = symbol)
    }

}