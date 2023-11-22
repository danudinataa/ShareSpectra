package com.ramaa.narutowiki.presentation.common

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
import com.ramaa.narutowiki.domain.model.*
import com.ramaa.narutowiki.presentation.home.components.CharacterCard
import com.ramaa.narutowiki.util.Dimens.ExtraSmallPadding2
import com.ramaa.narutowiki.util.Dimens.Padding1


@Composable
fun CharacterList(
    modifier: Modifier = Modifier,
    characters: List<Character>,
    onClick: (Character) -> Unit
) {
    if (characters.isEmpty()){
        EmptyScreen()
    }
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(Padding1),
        contentPadding = PaddingValues(all = ExtraSmallPadding2)
    ) {
        items(
            count = characters.size,
        ) {
            characters[it]?.let { character ->
                CharacterCard(character = character, onClick = { onClick(character) })
            }
        }
    }
}

@Composable
fun CharacterList(
    modifier: Modifier = Modifier,
    characters: LazyPagingItems<Character>,
    onClick:(Character) -> Unit
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
                    CharacterCard(character = character, onClick = {onClick(character)})
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(articles: LazyPagingItems<Character>): Boolean {
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

@Composable
fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(Padding1)){
        repeat(10){
            CardShimmerEffect(
                modifier = Modifier.padding(horizontal = Padding1)
            )
        }
    }
}