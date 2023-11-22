package com.ramaa.narutowiki.presentation.home

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
import com.ramaa.narutowiki.domain.model.ItemCharacter
import com.ramaa.narutowiki.navgraph.Route
import com.ramaa.narutowiki.presentation.common.CharacterList
import com.ramaa.narutowiki.presentation.common.SearchBar
import com.ramaa.narutowiki.util.Dimens.Padding1

@Composable
fun HomeScreen(
    characters: LazyPagingItems<ItemCharacter>,
    navigateToSearch:(String) -> Unit,
    navigateToDetails: (ItemCharacter) -> Unit
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