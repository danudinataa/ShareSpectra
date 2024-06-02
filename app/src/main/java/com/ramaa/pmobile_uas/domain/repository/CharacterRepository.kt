package com.ramaa.pmobile_uas.domain.repository

import androidx.paging.PagingData
import com.ramaa.pmobile_uas.data.remote.response.CompanyResponse
import com.ramaa.pmobile_uas.data.remote.response.ResultsNewsItem
import com.ramaa.pmobile_uas.data.remote.response.ResultsStockItem
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharacter(): Flow<PagingData<ResultsStockItem>>

    fun searchCharacter(searchQuery: String): Flow<PagingData<CompanyResponse>>

    suspend fun upsertCharacter(itemCharacter: ResultsStockItem)

    suspend fun deleteCharacter(itemCharacter: ResultsStockItem)

    fun getCharacters(): Flow<List<ResultsStockItem>>

    suspend fun getCharacter(symbol: String): ResultsStockItem?

    fun getNews(): Flow<PagingData<ResultsNewsItem>>

}