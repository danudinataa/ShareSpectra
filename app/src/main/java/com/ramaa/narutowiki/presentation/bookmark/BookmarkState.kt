package com.ramaa.narutowiki.presentation.bookmark

import com.ramaa.narutowiki.domain.model.ItemCharacter

data class BookmarkState(
    val itemCharacters: List<ItemCharacter> = emptyList()
)