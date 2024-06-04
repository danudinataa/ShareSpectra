package com.ramaa.pmobile_uas.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ramaa.pmobile_uas.presentation.detail.DetailsCompanyEvent
import com.ramaa.pmobile_uas.util.UIComponent
import dagger.hilt.android.lifecycle.HiltViewModel
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