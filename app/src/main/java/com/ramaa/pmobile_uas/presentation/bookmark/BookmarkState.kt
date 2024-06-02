package com.ramaa.pmobile_uas.presentation.bookmark

import com.ramaa.pmobile_uas.data.remote.response.ResultsStockItem
import com.ramaa.pmobile_uas.domain.model.ItemCharacter

data class BookmarkState(
    val itemCharacters: List<ResultsStockItem> = emptyList()
)