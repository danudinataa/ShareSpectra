package com.ramaa.narutowiki.presentation.home.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ramaa.narutowiki.R
import com.ramaa.narutowiki.domain.model.ItemCharacter
import com.ramaa.narutowiki.ui.theme.NarutoWikiTheme
import com.ramaa.narutowiki.util.Constants.IMAGE_NOT_FOUND
import com.ramaa.narutowiki.util.Dimens.CharacterCardSize
import com.ramaa.narutowiki.util.Dimens.ExtraSmallPadding
import com.ramaa.narutowiki.util.Dimens.ExtraSmallPadding2
import com.ramaa.narutowiki.util.Dimens.SmallIconSize

@Composable
fun CharacterCard(
    modifier: Modifier = Modifier,
    itemCharacter: ItemCharacter,
    onClick: (() -> Unit)? = null
) {

    val context = LocalContext.current
    Row(
        modifier = modifier.clickable { onClick?.invoke() },

        ) {
        AsyncImage(
            modifier = Modifier
                .size(CharacterCardSize)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(
                itemCharacter.images?.firstOrNull() ?: IMAGE_NOT_FOUND
            ).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = ExtraSmallPadding)
                .height(CharacterCardSize)
        ) {
            Text(
                text = itemCharacter.name,
                style = MaterialTheme.typography.bodyMedium.copy(),
                color = colorResource(id = R.color.text_title),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = itemCharacter.jutsu?.firstOrNull() ?: "Unknown",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body)
                )
                Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                Icon(
                    painter = painterResource(id = R.drawable.ic_chakra_logo),
                    contentDescription = null,
                    modifier = Modifier.size(SmallIconSize),
                    tint = colorResource(id = R.color.body)
                )
                Spacer(modifier = Modifier.width(ExtraSmallPadding))
                Text(
                    text = itemCharacter.natureType?.firstOrNull() ?: "Unknown",
                    style = MaterialTheme.typography.labelSmall,
                    color = colorResource(id = R.color.body)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardPreview() {
    NarutoWikiTheme(dynamicColor = false) {
        CharacterCard(
            itemCharacter = ItemCharacter(
                name = "Naruto",
                jutsu = listOf("Rasengan", "Rasen Shuriken"),
                natureType = listOf("Wind", "Yin"),
                id = 0,
                images = listOf(
                    "https://static.wikia.nocookie.net/naruto/images/d/d6/Naruto_Part_I.png",
                    "https://static.wikia.nocookie.net/naruto/images/7/7d/Naruto_Part_II.png"
                ),
                uniqueTraits = listOf("BLABLABLABALBA")
            )
        )
    }
}