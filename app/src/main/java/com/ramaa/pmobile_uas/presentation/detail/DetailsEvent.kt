package com.ramaa.pmobile_uas.presentation.detail

import com.ramaa.pmobile_uas.data.remote.response.ResultsStockItem

sealed class DetailsEvent {

    data class UpsertDeleteStock(val itemStock: ResultsStockItem) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()

}