package com.ramaa.narutowiki.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ramaa.narutowiki.domain.usecases.characters.CharacterUseCases
import com.ramaa.narutowiki.presentation.home.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val characterUseCases: CharacterUseCases
): ViewModel() {

    var state = mutableStateOf(HomeState())
        private set

    val characters = characterUseCases.getCharacters().cachedIn(viewModelScope)

}