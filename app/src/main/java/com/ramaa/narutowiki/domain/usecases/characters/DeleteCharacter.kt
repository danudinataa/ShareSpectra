package com.ramaa.narutowiki.domain.usecases.characters

import com.ramaa.narutowiki.data.local.CharactersDao
import com.ramaa.narutowiki.domain.model.Character
import javax.inject.Inject

class DeleteCharacter @Inject constructor (
    private val charactersDao: CharactersDao
) {

    suspend operator fun invoke(character: Character){
        charactersDao.delete(character = character)
    }

}