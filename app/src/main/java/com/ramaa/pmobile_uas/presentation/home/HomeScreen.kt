package com.ramaa.pmobile_uas.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.ramaa.pmobile_uas.data.remote.response.ResultsStockItem
import com.ramaa.pmobile_uas.navgraph.Route
import com.ramaa.pmobile_uas.presentation.common.CharacterList
import com.ramaa.pmobile_uas.presentation.common.SearchBar
import com.ramaa.pmobile_uas.util.Dimens.Padding1

@Composable
fun HomeScreen(
    characters: LazyPagingItems<ResultsStockItem>,
    navigateToSearch:(String) -> Unit,
    navigateToDetails: (ResultsStockItem) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = Padding1)
            .statusBarsPadding()
    ) {

        Spacer(modifier = Modifier.height(Padding1))

        SearchBar(
            modifier = Modifier
                .padding(horizontal = Padding1)
                .fillMaxWidth(),
            text = "",
            readOnly = true,
            onValueChange = {},
            onSearch = {},
            onClick = { navigateToSearch(Route.SearchScreen.route) }
        )

        Spacer(modifier = Modifier.height(Padding1))

        CharacterList(
            modifier = Modifier.padding(horizontal = Padding1),
            characters = characters,
            onClick = navigateToDetails
        )
    }
}