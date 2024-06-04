package com.ramaa.pmobile_uas.presentation.search.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ramaa.pmobile_uas.R
import com.ramaa.pmobile_uas.data.remote.response.CompanyResponse
import com.ramaa.pmobile_uas.util.Constants
import com.ramaa.pmobile_uas.util.Dimens

@Composable
fun CompanyCard(
    modifier: Modifier = Modifier,
    itemCompanies: CompanyResponse,
    onClick: (() -> Unit)? = null
) {

    val context = LocalContext.current
    Row(
        modifier = modifier.clickable { onClick?.invoke() }
    ) {
        AsyncImage(
            modifier = Modifier
                .size(Dimens.CharacterCardSize)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(
                itemCompanies.data?.logo ?: Constants.IMAGE_NOT_FOUND
            ).build(),
            contentDescription = null,
            contentScale = ContentScale.Fit // Ensure the entire image is visible without cropping
        )
        Spacer(modifier = Modifier.padding(Dimens.SmallPadding1))
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = Dimens.ExtraSmallPadding)
                .height(Dimens.CharacterCardSize)
                .align(Alignment.CenterVertically)
                .widthIn(max = 140.dp)
        ) {
            itemCompanies.data?.symbol?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleLarge.copy(),
                    color = colorResource(id = R.color.text_title),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            itemCompanies.data?.name.toString().let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(id = R.color.body),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .border(1.dp, color = MaterialTheme.colorScheme.primary, shape = MaterialTheme.shapes.small)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .widthIn(min = 40.dp, max = 40.dp)
        ) {
            Text(
                text = "${itemCompanies.data?.ipoPercentage.toString()}%",
                style = MaterialTheme.typography.labelMedium,
                color = colorResource(id = R.color.body),
                maxLines = 1,
            )
        }
    }
}