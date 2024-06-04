package com.ramaa.pmobile_uas.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.ramaa.pmobile_uas.data.remote.response.CompanyResponse
import com.ramaa.pmobile_uas.presentation.search.component.CompanyCard
import com.ramaa.pmobile_uas.util.Dimens.ExtraSmallPadding2
import com.ramaa.pmobile_uas.util.Dimens.Padding1

@Composable
fun CompanyList(
    modifier: Modifier = Modifier,
    companies: LazyPagingItems<CompanyResponse>,
    onClick:(CompanyResponse) -> Unit
) {

    val handlePagingResult = handlePagingResult(companies)

    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Padding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)
        ) {
            items(
                count = companies.itemCount,
            ) {
                companies[it]?.let { character ->
                    CompanyCard(itemCompanies = character, onClick = {onClick(character)})
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(companies: LazyPagingItems<CompanyResponse>): Boolean {
    val loadState = companies.loadState
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
