package com.ramaa.pmobile_uas.domain.repository

import androidx.paging.PagingData
import com.ramaa.pmobile_uas.data.remote.response.ResultsCompanies
import com.ramaa.pmobile_uas.data.remote.response.ResultsStockItem
import com.ramaa.pmobile_uas.domain.model.ItemCharacter
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharacter(): Flow<PagingData<ResultsStockItem>>

    fun searchCharacter(searchQuery: String): Flow<PagingData<ResultsCompanies>>

    suspend fun upsertCharacter(itemCharacter: ResultsStockItem)

    suspend fun deleteCharacter(itemCharacter: ResultsStockItem)

    fun getCharacters(): Flow<List<ResultsStockItem>>

    suspend fun getCharacter(symbol: String): ResultsStockItem?
}