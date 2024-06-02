package com.ramaa.pmobile_uas.domain.usecases.characters

import androidx.paging.PagingData
import com.ramaa.pmobile_uas.data.remote.response.ResultsCompanies
import com.ramaa.pmobile_uas.domain.model.ItemCharacter
import com.ramaa.pmobile_uas.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchCharacters @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    operator fun invoke(searchQuery: String): Flow<PagingData<ResultsCompanies>> {
        return characterRepository.searchCharacter(
            searchQuery = searchQuery
        )
    }
}