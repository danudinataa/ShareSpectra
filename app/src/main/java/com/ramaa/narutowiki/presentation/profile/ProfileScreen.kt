package com.ramaa.narutowiki.presentation.profile

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramaa.narutowiki.R

@Composable
fun ProfileScreen() {

    val context = LocalContext.current

    Column(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.padding(32.dp))

        Image (
            painter = painterResource(id = R.drawable.picture_profile),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(200.dp)
                .border(2.dp, Color.Gray, CircleShape)
        )

        Text (
            text = "Rama Danudinata",
            style = MaterialTheme.typography.titleLarge,
            color = colorResource(
                id = R.color.text_title
            ),
            modifier = Modifier
                .padding(top = 16.dp)
        )

        Text (
            text = "danunata45@gmail.com",
            style = MaterialTheme.typography.labelMedium,
            color = colorResource(
                id = R.color.text_medium
            ),
            modifier = Modifier
                .padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.padding(32.dp))

        Button (
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/ramadanudinata/"))
                intent.setPackage("com.linkedin.android")
                try {
                    context.startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    intent.setPackage(null)
                    context.startActivity(intent)
                }},
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 12.dp)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.shimmer)
            ),
            shape = RoundedCornerShape(20)
        ) {
            Column (
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_linkedin_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable {}
                )
            }
            Column (
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text (
                    text = "My LinkedIn",
                    style = MaterialTheme.typography.labelMedium,
                    color = colorResource(id = R.color.text_medium),
                )
            }
        }

        Button (
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/ini_nataa"))
                intent.setPackage("com.instagram.android")
                try {
                    context.startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    intent.setPackage(null)
                    context.startActivity(intent)
                }},
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 12.dp)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.shimmer)
            ),
            shape = RoundedCornerShape(20)
        ) {
            Column (
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_instagram_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable {}
                )
            }
            Column (
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text (
                    text = "My Instagram",
                    style = MaterialTheme.typography.labelMedium,
                    color = colorResource(id = R.color.text_medium),
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.about_me),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                maxLines = 2
            )
        }

    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}