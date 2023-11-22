package com.ramaa.narutowiki.domain.usecases.characters

import androidx.paging.PagingData
import com.ramaa.narutowiki.domain.model.ItemCharacter
import com.ramaa.narutowiki.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListCharacters @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    operator fun invoke(): Flow<PagingData<ItemCharacter>> {
        return characterRepository.getCharacter()
    }
}