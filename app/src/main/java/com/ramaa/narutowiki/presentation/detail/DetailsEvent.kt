package com.ramaa.narutowiki.presentation.detail

import com.ramaa.narutowiki.domain.model.ItemCharacter

sealed class DetailsEvent {

    data class UpsertDeleteCharacter(val itemCharacter: ItemCharacter) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()

}