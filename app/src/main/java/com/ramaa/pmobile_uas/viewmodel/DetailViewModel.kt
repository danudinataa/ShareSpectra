package com.ramaa.pmobile_uas.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramaa.pmobile_uas.data.remote.response.ResultsStockItem
import com.ramaa.pmobile_uas.domain.usecases.stocks.DeleteStock
import com.ramaa.pmobile_uas.domain.usecases.stocks.GetSavedStock
import com.ramaa.pmobile_uas.domain.usecases.stocks.UpsertStock
import com.ramaa.pmobile_uas.presentation.detail.DetailsEvent
import com.ramaa.pmobile_uas.util.UIComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getSavedStockUseCase: GetSavedStock,
    private val deleteStockUseCase: DeleteStock,
    private val upsertStockUseCase: UpsertStock
) : ViewModel() {

    var sideEffect by mutableStateOf<UIComponent?>(null)
        private set

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.UpsertDeleteStock -> {
                viewModelScope.launch {
                    val article = getSavedStockUseCase(symbol = event.itemStock.symbol)
                    if (article == null){
                        upsertStock(itemStock = event.itemStock)
                    }else{
                        deleteStock(itemStock = event.itemStock)
                    }
                }
            }
            is DetailsEvent.RemoveSideEffect ->{
                sideEffect = null
            }
        }
    }

    private suspend fun deleteStock(itemStock: ResultsStockItem) {
        deleteStockUseCase(itemStock = itemStock)
        sideEffect = UIComponent.Toast("Character Deleted from Bookmark!")
    }

    private suspend fun upsertStock(itemStock: ResultsStockItem) {
        upsertStockUseCase(itemStock = itemStock)
        sideEffect = UIComponent.Toast("Character Added to Bookmark!")
    }

}