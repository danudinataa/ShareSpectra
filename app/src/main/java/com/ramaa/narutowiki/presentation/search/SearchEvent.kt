package com.ramaa.narutowiki.presentation.search

sealed class SearchEvent {

    data class UpdateSearchQuery(val searchQuery: String) : SearchEvent()

    object SearchCharacters : SearchEvent()
}