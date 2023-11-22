package com.ramaa.narutowiki.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ramaa.narutowiki.data.local.CharactersDao
import com.ramaa.narutowiki.data.remote.CharacterPagingSource
import com.ramaa.narutowiki.data.remote.NarutoAPI
import com.ramaa.narutowiki.data.remote.SearchPagingSource
import com.ramaa.narutowiki.domain.model.Character
import com.ramaa.narutowiki.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val narutoAPI: NarutoAPI,
    private val charactersDao: CharactersDao
): CharacterRepository {

    override fun getCharacter(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                CharacterPagingSource(narutoAPI = narutoAPI)
            }
        ).flow
    }

    override fun searchCharacter(searchQuery: String): Flow<PagingData<Character>> {
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

    override suspend fun upsertCharacter(character: Character) {
        charactersDao.upsert(character)
    }

    override suspend fun deleteCharacter(character: Character) {
        charactersDao.delete(character)
    }

    override fun getCharacters(): Flow<List<Character>> {
        return charactersDao.getCharacters()
    }

    override suspend fun getCharacter(id: Int): Character? {
        return charactersDao.getCharacter(id = id)
    }
}