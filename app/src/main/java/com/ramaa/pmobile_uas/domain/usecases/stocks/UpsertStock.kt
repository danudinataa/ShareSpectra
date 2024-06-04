package com.ramaa.pmobile_uas.domain.usecases.stocks

import com.ramaa.pmobile_uas.data.local.StocksDao
import com.ramaa.pmobile_uas.data.remote.response.ResultsStockItem
import javax.inject.Inject

class UpsertStock @Inject constructor(
    private val stocksDao: StocksDao
) {

    suspend operator fun invoke(itemStock: ResultsStockItem){
        stocksDao.upsert(itemStock = itemStock)
    }

}