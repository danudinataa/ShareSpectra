package com.ramaa.pmobile_uas.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ramaa.pmobile_uas.data.local.StocksDao
import com.ramaa.pmobile_uas.data.remote.StocksPagingSource
import com.ramaa.pmobile_uas.data.remote.StockAPI
import com.ramaa.pmobile_uas.data.remote.NewsPagingSource
import com.ramaa.pmobile_uas.data.remote.SearchPagingSource
import com.ramaa.pmobile_uas.data.remote.response.CompanyResponse
import com.ramaa.pmobile_uas.data.remote.response.ResultsNewsItem
import com.ramaa.pmobile_uas.data.remote.response.ResultsStockItem
import com.ramaa.pmobile_uas.domain.repository.StockRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StockRepositoryImpl @Inject constructor(
    private val stockAPI: StockAPI,
    private val stocksDao: StocksDao
): StockRepository {

    override fun getStock(): Flow<PagingData<ResultsStockItem>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                StocksPagingSource(stockAPI = stockAPI)
            }
        ).flow
    }
    override fun getNews(): Flow<PagingData<ResultsNewsItem>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false)
        ) {
            NewsPagingSource(stockAPI)
        }.flow
    }

    override fun searchCompany(searchQuery: String): Flow<PagingData<CompanyResponse>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchPagingSource(
                    api = stockAPI,
                    searchQuery = searchQuery
                )
            }
        ).flow
    }

    override suspend fun upsertStock(itemCharacter: ResultsStockItem) {
        stocksDao.upsert(itemCharacter)
    }

    override suspend fun deleteStock(itemCharacter: ResultsStockItem) {
        stocksDao.delete(itemCharacter)
    }

    override fun getStocks(): Flow<List<ResultsStockItem>> {
        return stocksDao.getStocks()
    }

    override suspend fun getStock(symbol: String): ResultsStockItem? {
        return stocksDao.getStock(symbol = symbol)
    }
}