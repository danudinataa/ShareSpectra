package com.ramaa.narutowiki.presentation.bookmark

import com.ramaa.narutowiki.domain.model.Character

data class BookmarkState(
    val characters: List<Character> = emptyList()
)