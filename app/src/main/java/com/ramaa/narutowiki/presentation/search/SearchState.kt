package com.ramaa.narutowiki.presentation.search

import androidx.paging.PagingData
import com.ramaa.narutowiki.domain.model.Character
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Character>>? = null
)