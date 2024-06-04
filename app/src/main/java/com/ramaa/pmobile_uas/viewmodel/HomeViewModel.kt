package com.ramaa.pmobile_uas.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ramaa.pmobile_uas.domain.usecases.stocks.GetListStocks
import com.ramaa.pmobile_uas.presentation.home.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    listStocksUseCases: GetListStocks
): ViewModel() {

    var state = mutableStateOf(HomeState())
        private set

    val stocks = listStocksUseCases().cachedIn(viewModelScope)

}