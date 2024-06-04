package com.ramaa.pmobile_uas.presentation.detail

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ramaa.pmobile_uas.R
import com.ramaa.pmobile_uas.data.remote.response.ResultsStockItem
import com.ramaa.pmobile_uas.presentation.detail.components.DetailsTopBar
import com.ramaa.pmobile_uas.util.Constants
import com.ramaa.pmobile_uas.util.Dimens.CharacterImageHeight
import com.ramaa.pmobile_uas.util.Dimens.Padding1
import com.ramaa.pmobile_uas.util.Dimens.Padding2
import com.ramaa.pmobile_uas.util.Dimens.SmallPadding1
import com.ramaa.pmobile_uas.util.UIComponent

@Composable
fun DetailsScreen(
    itemStock: ResultsStockItem,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit,
    sideEffect: UIComponent?
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = sideEffect) {
        sideEffect?.let {
            when (sideEffect) {
                is UIComponent.Toast -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                    event(DetailsEvent.RemoveSideEffect)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(
            onBookmarkClick = {
                event(DetailsEvent.UpsertDeleteStock(itemStock))
            },
            onBackClick = navigateUp
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = Padding1,
                end = Padding1,
                top = Padding1
            )
        ) {
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(
                        itemStock.company?.logo ?: Constants.IMAGE_NOT_FOUND
                    ).build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(CharacterImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.height(Padding1))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = itemStock.symbol,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }
                Spacer(modifier = Modifier.height(Padding2))
                itemStock.company?.name?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleLarge,
                        color = colorResource(id = R.color.text_title)
                    )
                }
                Spacer(modifier = Modifier.height(Padding1))
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    itemStock.close?.toString()?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.displayLarge
                        )
                    }
                    Spacer(modifier = Modifier.height(SmallPadding1))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = itemStock.change.toString(),
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                            color = colorResource(id = R.color.body)
                        )
                        Spacer(modifier = Modifier.width(SmallPadding1))
                        Text(
                            text = "(${itemStock.percent.toString()}%) Hari ini",
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                            color = colorResource(id = R.color.body)
                        )
                    }
                }
            }
        }
    }
}
