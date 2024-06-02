package com.ramaa.pmobile_uas.presentation.news.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ramaa.pmobile_uas.R
import com.ramaa.pmobile_uas.data.remote.response.Company
import com.ramaa.pmobile_uas.data.remote.response.ResultsNewsItem
import com.ramaa.pmobile_uas.data.remote.response.ResultsStockItem
import com.ramaa.pmobile_uas.util.Constants.IMAGE_NOT_FOUND
import com.ramaa.pmobile_uas.util.Dimens.CharacterCardSize
import com.ramaa.pmobile_uas.util.Dimens.ExtraSmallPadding
import com.ramaa.pmobile_uas.util.Dimens.SmallIconSize
import com.ramaa.pmobile_uas.util.Dimens.SmallPadding1

@Composable
fun NewsCard(
    modifier: Modifier = Modifier,
    itemCharacter: ResultsNewsItem,
    onClick: (() -> Unit)? = null
) {

    val context = LocalContext.current
    Row(
        modifier = modifier.clickable { onClick?.invoke() }
    ) {
        AsyncImage(
            modifier = Modifier
                .size(CharacterCardSize)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(
                itemCharacter.image ?: IMAGE_NOT_FOUND
            ).build(),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.padding(SmallPadding1))
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = ExtraSmallPadding)
                .height(CharacterCardSize)
                .align(Alignment.CenterVertically)
        ) {
            itemCharacter.title?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleSmall.copy(),
                    color = colorResource(id = R.color.text_title),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            itemCharacter.description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(id = R.color.body),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCharacterCard() {
    val sampleItemCharacter = ResultsNewsItem(
        title = "Solusi Lifestyle Mudah! Bank Mandiri-JCB Gelar Festival Menarik Ini",
        image = "https://awsimages.detik.net.id/visual/2024/06/01/dok-bank-mandiri_169.jpeg?w=650",
        description = "Bank Mandiri dan JCB telah berkolaborasi dalam menyajikan rangkaian Mandiri JCB Precious Festival 2024"
    )
    NewsCard(
        modifier = Modifier.padding(16.dp),
        itemCharacter = sampleItemCharacter,
        onClick = { /* Handle click */ }
    )
}