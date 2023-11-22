package com.ramaa.narutowiki.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramaa.narutowiki.domain.model.ItemCharacter
import com.ramaa.narutowiki.domain.usecases.characters.DeleteCharacter
import com.ramaa.narutowiki.domain.usecases.characters.GetSavedCharacter
import com.ramaa.narutowiki.domain.usecases.characters.UpsertCharacter
import com.ramaa.narutowiki.presentation.detail.DetailsEvent
import com.ramaa.narutowiki.util.UIComponent
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
                    val article = getSavedCharacterUseCase(id = event.itemCharacter.id)
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

    private suspend fun deleteCharacter(itemCharacter: ItemCharacter) {
        deleteCharacterUseCase(itemCharacter = itemCharacter)
        sideEffect = UIComponent.Toast("Character Deleted from Bookmark!")
    }

    private suspend fun upsertCharacter(itemCharacter: ItemCharacter) {
        upsertCharacterUseCase(itemCharacter = itemCharacter)
        sideEffect = UIComponent.Toast("Character Added to Bookmark!")
    }

}