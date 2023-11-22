package com.ramaa.narutowiki.presentation.search

import androidx.paging.PagingData
import com.ramaa.narutowiki.domain.model.ItemCharacter
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val characters: Flow<PagingData<ItemCharacter>>? = null
)