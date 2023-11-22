package com.ramaa.narutowiki.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ramaa.narutowiki.data.local.CharactersDao
import com.ramaa.narutowiki.data.remote.CharacterPagingSource
import com.ramaa.narutowiki.data.remote.NarutoAPI
import com.ramaa.narutowiki.data.remote.SearchPagingSource
import com.ramaa.narutowiki.domain.model.ItemCharacter
import com.ramaa.narutowiki.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val narutoAPI: NarutoAPI,
    private val charactersDao: CharactersDao
): CharacterRepository {

    override fun getCharacter(): Flow<PagingData<ItemCharacter>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                CharacterPagingSource(narutoAPI = narutoAPI)
            }
        ).flow
    }

    override fun searchCharacter(searchQuery: String): Flow<PagingData<ItemCharacter>> {
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

    override suspend fun upsertCharacter(itemCharacter: ItemCharacter) {
        charactersDao.upsert(itemCharacter)
    }

    override suspend fun deleteCharacter(itemCharacter: ItemCharacter) {
        charactersDao.delete(itemCharacter)
    }

    override fun getCharacters(): Flow<List<ItemCharacter>> {
        return charactersDao.getCharacters()
    }

    override suspend fun getCharacter(id: Int): ItemCharacter? {
        return charactersDao.getCharacter(id = id)
    }
}