package com.ramaa.pmobile_uas.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.ramaa.pmobile_uas.data.remote.response.ResultsCompanies
import com.ramaa.pmobile_uas.data.remote.response.ResultsStockItem
import com.ramaa.pmobile_uas.presentation.home.components.CharacterCard
import com.ramaa.pmobile_uas.presentation.search.component.CompanyCard
import com.ramaa.pmobile_uas.util.Dimens.ExtraSmallPadding2
import com.ramaa.pmobile_uas.util.Dimens.Padding1


@Composable
fun CompanyList(
    modifier: Modifier = Modifier,
    itemCharacters: List<ResultsCompanies>,
    onClick: (ResultsCompanies) -> Unit
) {
    if (itemCharacters.isEmpty()){
        EmptyScreen()
    }
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(Padding1),
        contentPadding = PaddingValues(all = ExtraSmallPadding2)
    ) {
        items(
            count = itemCharacters.size,
        ) {
            itemCharacters[it].let { character ->
                CompanyCard(itemCharacter = character, onClick = { onClick(character) })
            }
        }
    }
}

@Composable
fun CompanyList(
    modifier: Modifier = Modifier,
    characters: LazyPagingItems<ResultsCompanies>,
    onClick:(ResultsCompanies) -> Unit
) {

    val handlePagingResult = handlePagingResult(characters)

    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Padding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)
        ) {
            items(
                count = characters.itemCount,
            ) {
                characters[it]?.let { character ->
                    CompanyCard(itemCharacter = character, onClick = {onClick(character)})
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(articles: LazyPagingItems<ResultsCompanies>): Boolean {
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen(error = error)
            false
        }

        else -> {
            true
        }
    }
}
