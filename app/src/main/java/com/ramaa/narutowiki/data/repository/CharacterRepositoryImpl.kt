package com.ramaa.narutowiki.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ramaa.narutowiki.data.remote.CharacterPagingSource
import com.ramaa.narutowiki.data.remote.NarutoAPI
import com.ramaa.narutowiki.data.remote.response.SearchCharacterPagingSource
import com.ramaa.narutowiki.domain.model.Character
import com.ramaa.narutowiki.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class CharacterRepositoryImpl(private val narutoAPI: NarutoAPI): CharacterRepository {

    override fun getCharacter(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                CharacterPagingSource(narutoAPI = narutoAPI)
            }
        ).flow
    }

    override fun searchCharacter(searchQuery: String, sources: List<String>): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchCharacterPagingSource(
                    api = narutoAPI,
                    searchQuery = searchQuery,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }


}