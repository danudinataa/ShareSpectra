package com.ramaa.narutowiki.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramaa.narutowiki.domain.usecases.characters.GetSavedCharacter
import com.ramaa.narutowiki.domain.usecases.characters.GetSavedCharacters
import com.ramaa.narutowiki.presentation.bookmark.BookmarkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getSavedCharacterUseCase: GetSavedCharacters
) : ViewModel() {

    private val _state = mutableStateOf(BookmarkState())
    val state: State<BookmarkState> = _state

    init {
        getCharacters()
    }

    private fun getCharacters() {
        getSavedCharacterUseCase().onEach {
            _state.value = _state.value.copy(characters = it)
        }.launchIn(viewModelScope)
    }
}