package com.ramaa.pmobile_uas.domain.repository

import androidx.paging.PagingData
import com.ramaa.pmobile_uas.data.remote.response.CompanyResponse
import com.ramaa.pmobile_uas.data.remote.response.ResultsNewsItem
import com.ramaa.pmobile_uas.data.remote.response.ResultsStockItem
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    fun getStock(): Flow<PagingData<ResultsStockItem>>

    fun searchCompany(searchQuery: String): Flow<PagingData<CompanyResponse>>

    suspend fun upsertStock(itemCharacter: ResultsStockItem)

    suspend fun deleteStock(itemCharacter: ResultsStockItem)

    fun getStocks(): Flow<List<ResultsStockItem>>

    suspend fun getStock(symbol: String): ResultsStockItem?

    fun getNews(): Flow<PagingData<ResultsNewsItem>>

}