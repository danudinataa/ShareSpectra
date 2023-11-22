package com.ramaa.narutowiki.domain.repository

import androidx.paging.PagingData
import com.ramaa.narutowiki.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharacter(): Flow<PagingData<Character>>

    fun searchCharacter(searchQuery: String): Flow<PagingData<Character>>

    suspend fun upsertCharacter(character: Character)

    suspend fun deleteCharacter(character: Character)

    fun getCharacters(): Flow<List<Character>>

    suspend fun getCharacter(id: Int): Character?
}