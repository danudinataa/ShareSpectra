package com.ramaa.narutowiki.presentation.detail

import com.ramaa.narutowiki.domain.model.Character

sealed class DetailsEvent {

    data class UpsertDeleteCharacter(val character: Character) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()

}