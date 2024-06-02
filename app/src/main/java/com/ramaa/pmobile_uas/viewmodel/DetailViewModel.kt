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
import com.ramaa.pmobile_uas.presentation.detail.DetailsEvent
import com.ramaa.pmobile_uas.util.UIComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getSavedCharacterUseCase: GetSavedCharacter ,
    private val deleteCharacterUseCase: DeleteCharacter,
    private val upsertCharacterUseCase: UpsertCharacter
) : ViewModel() {

    var sideEffect by mutableStateOf<UIComponent?>(null)
        private set

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.UpsertDeleteCharacter -> {
                viewModelScope.launch {
                    val article = getSavedCharacterUseCase(symbol = event.itemCharacter.symbol)
                    if (article == null){
                        upsertCharacter(itemCharacter = event.itemCharacter)
                    }else{
                        deleteCharacter(itemCharacter = event.itemCharacter)
                    }
                }
            }
            is DetailsEvent.RemoveSideEffect ->{
                sideEffect = null
            }
        }
    }

    private suspend fun deleteCharacter(itemCharacter: ResultsStockItem) {
        deleteCharacterUseCase(itemCharacter = itemCharacter)
        sideEffect = UIComponent.Toast("Character Deleted from Bookmark!")
    }

    private suspend fun upsertCharacter(itemCharacter: ResultsStockItem) {
        upsertCharacterUseCase(itemCharacter = itemCharacter)
        sideEffect = UIComponent.Toast("Character Added to Bookmark!")
    }

}