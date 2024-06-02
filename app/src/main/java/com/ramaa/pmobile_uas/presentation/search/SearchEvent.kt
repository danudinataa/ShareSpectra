package com.ramaa.pmobile_uas.presentation.search

sealed class SearchEvent {

    data class UpdateSearchQuery(val searchQuery: String) : SearchEvent()

    object SearchCharacters : SearchEvent()
}