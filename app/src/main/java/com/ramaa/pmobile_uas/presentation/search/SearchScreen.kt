package com.ramaa.pmobile_uas.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.ramaa.pmobile_uas.data.remote.response.CompanyResponse
import com.ramaa.pmobile_uas.presentation.common.CompanyList
import com.ramaa.pmobile_uas.presentation.common.SearchBar
import com.ramaa.pmobile_uas.util.Dimens.Padding1

@Composable
fun SearchScreen(
    state: SearchState,
    event:(SearchEvent) -> Unit,
    navigateToDetailsCompanies: (CompanyResponse) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(top = Padding1, start = Padding1, end = Padding1)
            .statusBarsPadding()
    ) {
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = {
                event(SearchEvent.SearchCharacters)
            }
        )
        Spacer(modifier = Modifier.height(Padding1))
        state.characters?.let {
            val characters = it.collectAsLazyPagingItems()
            CompanyList(
                characters = characters,
                onClick = { character ->
                    navigateToDetailsCompanies(character)
                }
            )
        }
    }
}