package com.ramaa.pmobile_uas.domain.usecases.characters

import com.ramaa.pmobile_uas.data.local.CharactersDao
import com.ramaa.pmobile_uas.data.remote.response.ResultsStockItem
import com.ramaa.pmobile_uas.domain.model.ItemCharacter
import javax.inject.Inject

class UpsertCharacter @Inject constructor(
    private val charactersDao: CharactersDao
) {

    suspend operator fun invoke(itemCharacter: ResultsStockItem){
        charactersDao.upsert(itemCharacter = itemCharacter)
    }

}