package com.ramaa.pmobile_uas.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ramaa.pmobile_uas.domain.usecases.stocks.SearchStocks
import com.ramaa.pmobile_uas.presentation.search.SearchEvent
import com.ramaa.pmobile_uas.presentation.search.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCases: SearchStocks
) : ViewModel() {

    private var _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state


    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateSearchQuery -> {
                _state.value = _state.value.copy(searchQuery = event.searchQuery)
            }

            is SearchEvent.SearchCompanies -> {
                searchCompanies()
            }
        }
    }

    private fun searchCompanies() {
        val companies = searchUseCases(
            searchQuery = _state.value.searchQuery
        ).cachedIn(viewModelScope)
        _state.value = _state.value.copy(companies = companies)
    }
}