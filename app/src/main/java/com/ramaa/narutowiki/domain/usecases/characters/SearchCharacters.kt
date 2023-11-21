package com.ramaa.narutowiki.domain.usecases.characters

import androidx.paging.PagingData
import com.ramaa.narutowiki.domain.model.Character
import com.ramaa.narutowiki.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class SearchCharacters(
    private val characterRepository: CharacterRepository
) {
    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Character>> {
        return characterRepository.searchCharacter(
            searchQuery = searchQuery,
            sources = sources
        )
    }
}