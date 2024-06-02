package com.ramaa.pmobile_uas.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramaa.pmobile_uas.data.remote.response.ResultsStockItem
import com.ramaa.pmobile_uas.domain.model.ItemCharacter
import com.ramaa.pmobile_uas.domain.usecases.characters.DeleteCharacter
import com.ramaa.pmobile_uas.domain.usecases.characters.GetSavedCharacter
import com.ramaa.pmobile_uas.domain.usecases.characters.UpsertCharacter
import com.ramaa.pmobile_uas.presentation.detail.DetailsCompanyEvent
import com.ramaa.pmobile_uas.presentation.detail.DetailsEvent
import com.ramaa.pmobile_uas.util.UIComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailCompanyViewModel @Inject constructor(
) : ViewModel() {

    var sideEffect by mutableStateOf<UIComponent?>(null)
        private set

    fun onEvent(event: DetailsCompanyEvent) {
        when (event) {
            is DetailsCompanyEvent.RemoveSideEffect ->{
                sideEffect = null
            }
        }
    }
}