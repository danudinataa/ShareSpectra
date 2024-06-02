package com.ramaa.pmobile_uas.domain.usecases.characters

import com.ramaa.pmobile_uas.data.local.CharactersDao
import com.ramaa.pmobile_uas.data.remote.response.ResultsStockItem
import com.ramaa.pmobile_uas.domain.model.ItemCharacter
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedCharacters @Inject constructor(
    private val charactersDao: CharactersDao
) {

    operator fun invoke(): Flow<List<ResultsStockItem>> {
        return charactersDao.getCharacters()
    }
}