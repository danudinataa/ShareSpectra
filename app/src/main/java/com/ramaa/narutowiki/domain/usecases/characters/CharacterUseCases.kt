package com.ramaa.narutowiki.domain.usecases.characters

data class CharacterUseCases(
    val getCharacters: GetCharacters,
    val searchCharacters: SearchCharacters
)