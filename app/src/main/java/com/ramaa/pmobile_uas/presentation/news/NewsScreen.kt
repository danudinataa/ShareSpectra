package com.ramaa.pmobile_uas.presentation.news

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
import com.ramaa.pmobile_uas.data.remote.response.ResultsNewsItem
import com.ramaa.pmobile_uas.data.remote.response.ResultsStockItem
import com.ramaa.pmobile_uas.navgraph.Route
import com.ramaa.pmobile_uas.presentation.common.CharacterList
import com.ramaa.pmobile_uas.presentation.common.NewsList
import com.ramaa.pmobile_uas.presentation.common.SearchBar
import com.ramaa.pmobile_uas.util.Dimens

@Composable
fun NewsScreen(
    characters: LazyPagingItems<ResultsNewsItem>,
    navigateToWebsite: (ResultsNewsItem) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = Dimens.Padding1)
            .statusBarsPadding()
    ) {

        Spacer(modifier = Modifier.height(Dimens.Padding1))

        NewsList(
            modifier = Modifier.padding(horizontal = Dimens.Padding1),
            characters = characters,
            onClick = navigateToWebsite
        )
    }
}
