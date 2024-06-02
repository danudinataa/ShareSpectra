package com.ramaa.pmobile_uas.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ramaa.pmobile_uas.data.local.CharactersDao
import com.ramaa.pmobile_uas.data.remote.CharacterPagingSource
import com.ramaa.pmobile_uas.data.remote.NarutoAPI
import com.ramaa.pmobile_uas.data.remote.SearchPagingSource
import com.ramaa.pmobile_uas.data.remote.response.CompanyResponse
import com.ramaa.pmobile_uas.data.remote.response.ResultsStockItem
import com.ramaa.pmobile_uas.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val narutoAPI: NarutoAPI,
    private val charactersDao: CharactersDao
): CharacterRepository {

    override fun getCharacter(): Flow<PagingData<ResultsStockItem>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                CharacterPagingSource(narutoAPI = narutoAPI)
            }
        ).flow
    }

    override fun searchCharacter(searchQuery: String): Flow<PagingData<CompanyResponse>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchPagingSource(
                    api = narutoAPI,
                    searchQuery = searchQuery
                )
            }
        ).flow
    }

    override suspend fun upsertCharacter(itemCharacter: ResultsStockItem) {
        charactersDao.upsert(itemCharacter)
    }

    override suspend fun deleteCharacter(itemCharacter: ResultsStockItem) {
        charactersDao.delete(itemCharacter)
    }

    override fun getCharacters(): Flow<List<ResultsStockItem>> {
        return charactersDao.getCharacters()
    }

    override suspend fun getCharacter(symbol: String): ResultsStockItem? {
        return charactersDao.getCharacter(symbol = symbol)
    }
}