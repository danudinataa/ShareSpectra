package com.ramaa.pmobile_uas.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramaa.pmobile_uas.domain.usecases.stocks.GetSavedCharacters
import com.ramaa.pmobile_uas.presentation.bookmark.BookmarkState
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
        getStocks()
    }

    private fun getStocks() {
        getSavedCharacterUseCase().onEach {
            _state.value = _state.value.copy(itemStocks = it)
        }.launchIn(viewModelScope)
    }
}