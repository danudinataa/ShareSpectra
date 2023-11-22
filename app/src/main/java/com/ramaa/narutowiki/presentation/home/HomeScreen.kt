package com.ramaa.narutowiki.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.ramaa.narutowiki.R
import com.ramaa.narutowiki.domain.model.Character
import com.ramaa.narutowiki.presentation.common.CharacterList
import com.ramaa.narutowiki.presentation.common.SearchBar
import com.ramaa.narutowiki.util.Dimens.Padding1

@Composable
fun HomeScreen(
    characters: LazyPagingItems<Character>,
    navigateToSearch:() -> Unit,
    navigateToDetails: (Character) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = Padding1)
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_naruto_wiki_logo),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = Padding1)
        )

        Spacer(modifier = Modifier.height(Padding1))

        SearchBar(
            modifier = Modifier
                .padding(horizontal = Padding1)
                .fillMaxWidth(),
            text = "",
            readOnly = true,
            onValueChange = {},
            onSearch = {},
            onClick = { navigateToSearch }
        )

        Spacer(modifier = Modifier.height(Padding1))

        CharacterList(
            modifier = Modifier.padding(horizontal = Padding1),
            characters = characters,
            onClick = navigateToDetails
        )
    }
}