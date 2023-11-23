package com.ramaa.narutowiki.presentation.detail

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ramaa.narutowiki.R
import com.ramaa.narutowiki.domain.model.ItemCharacter
import com.ramaa.narutowiki.presentation.detail.components.DetailsTopBar
import com.ramaa.narutowiki.util.Constants
import com.ramaa.narutowiki.util.Dimens
import com.ramaa.narutowiki.util.Dimens.CharacterImageHeight
import com.ramaa.narutowiki.util.Dimens.Padding1
import com.ramaa.narutowiki.util.Dimens.Padding2
import com.ramaa.narutowiki.util.Dimens.SmallPadding1
import com.ramaa.narutowiki.util.UIComponent

@Composable
fun DetailsScreen(
    itemCharacter: ItemCharacter,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit,
    sideEffect: UIComponent?
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = sideEffect) {
        sideEffect?.let {
            when(sideEffect){
                is UIComponent.Toast ->{
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                    event(DetailsEvent.RemoveSideEffect)
                }
            }
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()) {
        DetailsTopBar(
            onBookmarkClick = {
                event(DetailsEvent.UpsertDeleteCharacter(itemCharacter))
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
                        itemCharacter.images?.firstOrNull() ?: Constants.IMAGE_NOT_FOUND
                    )
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(CharacterImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(Padding1))
                Text(
                    text = itemCharacter.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = colorResource(
                        id = R.color.text_title
                    )
                )
                Spacer(modifier = Modifier.height(Padding1))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_jutsu_icon),
                        contentDescription = null,
                        modifier = Modifier.size(Dimens.IconSize),
                        tint = colorResource(id = R.color.body)
                    )
                    Spacer(modifier = Modifier.width(SmallPadding1))
                    Text(
                        text = "Jutsu",
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                        color = colorResource(id = R.color.body)
                    )
                }
                Spacer(modifier = Modifier.height(SmallPadding1))

                itemCharacter.jutsu?.takeIf { it.isNotEmpty() }?.forEachIndexed { index, item ->
                    Text(
                        text = "${index + 1}. $item",
                        style = MaterialTheme.typography.labelMedium,
                        color = colorResource(id = R.color.body),
                        modifier = Modifier.padding(start = Padding1)
                    )
                } ?: run {
                    Text(
                        text = "Unknown",
                        style = MaterialTheme.typography.labelMedium,
                        color = colorResource(id = R.color.body),
                        modifier = Modifier.padding(start = Padding1)
                    )
                }

                Spacer(modifier = Modifier.height(Padding2))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_chakra_logo),
                        contentDescription = null,
                        modifier = Modifier.size(Dimens.IconSize),
                        tint = colorResource(id = R.color.body)
                    )
                    Spacer(modifier = Modifier.width(SmallPadding1))
                    Text(
                        text = "Chakra",
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                        color = colorResource(id = R.color.body)
                    )
                }
                Spacer(modifier = Modifier.height(SmallPadding1))

                itemCharacter.natureType?.takeIf { it.isNotEmpty() }?.forEachIndexed { index, item ->
                    Text(
                        text = "${index + 1}. $item",
                        style = MaterialTheme.typography.labelMedium,
                        color = colorResource(id = R.color.body),
                        modifier = Modifier.padding(start = Padding1)
                    )
                } ?: run {
                    Text(
                        text = "Unknown",
                        style = MaterialTheme.typography.labelMedium,
                        color = colorResource(id = R.color.body),
                        modifier = Modifier.padding(start = Padding1)
                    )
                }
                
                Spacer(modifier = Modifier.height(Padding2))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_shuriken_icon),
                        contentDescription = null,
                        modifier = Modifier.size(Dimens.IconSize),
                        tint = colorResource(id = R.color.body)
                    )
                    Spacer(modifier = Modifier.width(SmallPadding1))
                    Text(
                        text = "Ninja Tools",
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                        color = colorResource(id = R.color.body)
                    )
                }
                Spacer(modifier = Modifier.height(SmallPadding1))

                itemCharacter.tools?.takeIf { it.isNotEmpty() }?.forEachIndexed { index, item ->
                    Text(
                        text = "${index + 1}. $item",
                        style = MaterialTheme.typography.labelMedium,
                        color = colorResource(id = R.color.body),
                        modifier = Modifier.padding(start = Padding1)
                    )
                } ?: run {
                    Text(
                        text = "Unknown",
                        style = MaterialTheme.typography.labelMedium,
                        color = colorResource(id = R.color.body),
                        modifier = Modifier.padding(start = Padding1)
                    )
                }

                Spacer(modifier = Modifier.height(Padding2))
            }
        }
    }
}