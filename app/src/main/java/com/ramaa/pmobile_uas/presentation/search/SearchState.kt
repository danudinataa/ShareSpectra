package com.ramaa.pmobile_uas.presentation.search

import androidx.paging.PagingData
import com.ramaa.pmobile_uas.data.remote.response.CompanyResponse
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val companies: Flow<PagingData<CompanyResponse>>? = null
)