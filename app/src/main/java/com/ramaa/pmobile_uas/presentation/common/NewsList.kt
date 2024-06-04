package com.ramaa.pmobile_uas.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.ramaa.pmobile_uas.data.remote.response.ResultsNewsItem
import com.ramaa.pmobile_uas.presentation.news.component.NewsCard
import com.ramaa.pmobile_uas.util.Dimens.ExtraSmallPadding2
import com.ramaa.pmobile_uas.util.Dimens.Padding1


@Composable
fun NewsList(
    modifier: Modifier = Modifier,
    itemStocks: List<ResultsNewsItem>,
    onClick: (ResultsNewsItem) -> Unit
) {
    if (itemStocks.isEmpty()){
        EmptyScreen()
    }
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(Padding1),
        contentPadding = PaddingValues(all = ExtraSmallPadding2)
    ) {
        items(
            count = itemStocks.size,
        ) {
            itemStocks[it].let { character ->
                NewsCard(itemNews = character, onClick = { onClick(character) })
            }
        }
    }
}

@Composable
fun NewsList(
    modifier: Modifier = Modifier,
    stocks: LazyPagingItems<ResultsNewsItem>,
    onClick: (ResultsNewsItem) -> Unit
) {
    val handlePagingResult = handlePagingResult(stocks)

    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Padding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)
        ) {
            items(
                count = stocks.itemCount,
            ) {
                stocks[it]?.let { character ->
                    NewsCard(itemNews = character, onClick = { onClick(character) })
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(articles: LazyPagingItems<ResultsNewsItem>): Boolean {
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