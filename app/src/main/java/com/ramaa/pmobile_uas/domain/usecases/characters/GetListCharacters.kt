package com.ramaa.pmobile_uas.domain.usecases.characters

import androidx.paging.PagingData
import com.ramaa.pmobile_uas.data.remote.response.ResultsStockItem
import com.ramaa.pmobile_uas.domain.model.ItemCharacter
import com.ramaa.pmobile_uas.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListCharacters @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    operator fun invoke(): Flow<PagingData<ResultsStockItem>> {
        return characterRepository.getCharacter()
    }
}