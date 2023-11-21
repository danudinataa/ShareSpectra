package com.ramaa.narutowiki.domain.repository

import androidx.paging.PagingData
import com.ramaa.narutowiki.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharacter(): Flow<PagingData<Character>>

    fun searchCharacter(searchQuery: String, sources: List<String>): Flow<PagingData<Character>>
}