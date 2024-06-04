package com.ramaa.pmobile_uas.presentation.bookmark

import com.ramaa.pmobile_uas.data.remote.response.ResultsStockItem

data class BookmarkState(
    val itemStocks: List<ResultsStockItem> = emptyList()
)