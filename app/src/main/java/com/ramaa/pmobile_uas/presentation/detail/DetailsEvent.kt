package com.ramaa.pmobile_uas.presentation.detail

import com.ramaa.pmobile_uas.data.remote.response.ResultsStockItem
import com.ramaa.pmobile_uas.domain.model.ItemCharacter

sealed class DetailsEvent {

    data class UpsertDeleteCharacter(val itemCharacter: ResultsStockItem) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()

}