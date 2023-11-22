package com.ramaa.narutowiki.domain.repository

import androidx.paging.PagingData
import com.ramaa.narutowiki.domain.model.ItemCharacter
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharacter(): Flow<PagingData<ItemCharacter>>

    fun searchCharacter(searchQuery: String): Flow<PagingData<ItemCharacter>>

    suspend fun upsertCharacter(itemCharacter: ItemCharacter)

    suspend fun deleteCharacter(itemCharacter: ItemCharacter)

    fun getCharacters(): Flow<List<ItemCharacter>>

    suspend fun getCharacter(id: Int): ItemCharacter?
}