package com.ramaa.narutowiki.presentation.home

data class HomeState(
    val charactersTicker: String = "",
    val isLoading: Boolean = false,
)