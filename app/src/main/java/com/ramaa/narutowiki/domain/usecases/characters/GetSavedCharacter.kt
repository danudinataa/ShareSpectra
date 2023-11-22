package com.ramaa.narutowiki.domain.usecases.characters

import com.ramaa.narutowiki.data.local.CharactersDao
import com.ramaa.narutowiki.domain.model.ItemCharacter
import javax.inject.Inject

class GetSavedCharacter @Inject constructor(
    private val charactersDao: CharactersDao
) {

    suspend operator fun invoke(id: Int): ItemCharacter?{
        return charactersDao.getCharacter(id = id)
    }

}