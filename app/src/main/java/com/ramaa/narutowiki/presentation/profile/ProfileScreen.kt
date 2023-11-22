package com.ramaa.narutowiki.presentation.profile

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ramaa.narutowiki.R

@Composable
fun ProfileScreen() {
    Column(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ConstraintLayout {
            val(topImg, profile) = createRefs()
            Image (
                painter = painterResource(id = R.drawable.top_background),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(topImg) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
            )
            Image (
                painter = painterResource(id = R.drawable.picture_profile),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape)
                    .constrainAs(profile) {
                        top.linkTo(topImg.bottom)
                        bottom.linkTo(topImg.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
        }

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


    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}