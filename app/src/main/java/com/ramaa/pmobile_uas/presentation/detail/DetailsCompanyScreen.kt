package com.ramaa.pmobile_uas.presentation.detail

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ramaa.pmobile_uas.R
import com.ramaa.pmobile_uas.data.remote.response.CompanyResponse
import com.ramaa.pmobile_uas.presentation.detail.components.DetailsCompanyTopBar
import com.ramaa.pmobile_uas.util.Constants
import com.ramaa.pmobile_uas.util.Dimens
import com.ramaa.pmobile_uas.util.UIComponent

@Composable
fun DetailsCompanyScreen(
    itemCompany: CompanyResponse,
    event: (DetailsCompanyEvent) -> Unit,
    navigateUp: () -> Unit,
    sideEffect: UIComponent?
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = sideEffect) {
        sideEffect?.let {
            when(sideEffect){
                is UIComponent.Toast ->{
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                    event(DetailsCompanyEvent.RemoveSideEffect)
                }
            }
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()) {
        DetailsCompanyTopBar(
            onBackClick = navigateUp
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = Dimens.Padding1,
                end = Dimens.Padding1,
                top = Dimens.Padding1
            )
        ) {
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(
                        itemCompany.data?.logo ?: Constants.IMAGE_NOT_FOUND
                    ).build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimens.CharacterImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(Dimens.Padding1))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    itemCompany.data?.symbol?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(Dimens.Padding2))
                Row{
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .border(
                                1.dp,
                                color = MaterialTheme.colorScheme.primary,
                                shape = MaterialTheme.shapes.small
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        itemCompany.data?.sectorName?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.labelMedium,
                                color = colorResource(id = R.color.body),
                                maxLines = 1,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(Dimens.SmallPadding1))
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .border(
                                1.dp,
                                color = MaterialTheme.colorScheme.primary,
                                shape = MaterialTheme.shapes.small
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        itemCompany.data?.industryName?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.labelMedium,
                                color = colorResource(id = R.color.body),
                                maxLines = 1,
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(Dimens.Padding1))
                itemCompany.data?.name?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleLarge,
                        color = colorResource(id = R.color.text_title)
                    )
                }
                Spacer(modifier = Modifier.height(Dimens.Padding1))
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    itemCompany.data?.ipoOfferingShares.toString()?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.displayLarge
                        )
                    }
                    Spacer(modifier = Modifier.height(Dimens.SmallPadding1))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "(${itemCompany.data?.ipoPercentage.toString()}%)",
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                            color = colorResource(id = R.color.body)
                        )
                        Spacer(modifier = Modifier.width(Dimens.SmallPadding1))
                        itemCompany.data?.status?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                                color = colorResource(id = R.color.body)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(Dimens.Padding2))
                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse("https://${itemCompany.data?.website}")
                        }
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimens.Padding1)
                ) {
                    Text(text = "Visit Website")
                }
            }
        }
    }
}