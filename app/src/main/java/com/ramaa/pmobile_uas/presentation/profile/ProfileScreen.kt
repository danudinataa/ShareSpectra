package com.ramaa.pmobile_uas.presentation.profile

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.ramaa.pmobile_uas.R
import com.ramaa.pmobile_uas.navgraph.Route
import com.ramaa.pmobile_uas.presentation.login.SignInState
import com.ramaa.pmobile_uas.presentation.login.UserData
import com.ramaa.pmobile_uas.ui.theme.LightOrange
import com.ramaa.pmobile_uas.util.Dimens

@Composable
fun ProfileScreen(
    navController: NavController,
    userData: UserData?,
    onSignOut: () -> Unit,
    onSignIn: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.padding(32.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.Padding1)
        ) {
            Spacer(modifier = Modifier.weight(1f))

            FilledIconButton(
                onClick = { navController.navigate(route = Route.AboutScreen.route) },
            ) {
                Icon(painter = painterResource(id = R.drawable.baseline_info_24), contentDescription = "Info")
            }
        }

        Image(
            painter = painterResource(id = R.drawable.baseline_person_24),
            contentDescription = "picture_profile",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(200.dp)
                .border(2.dp, LightOrange, CircleShape)
        )

        /*if (userData?.profilePictureUrl != null) {
            AsyncImage(
                model = userData.profilePictureUrl,
                contentDescription = "picture_profile",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(200.dp)
                    .border(2.dp, LightOrange, CircleShape)
            )
        }*/

        Text(
            text = userData?.username ?: "Nama Pengguna",
            style = MaterialTheme.typography.titleLarge,
            color = colorResource(id = R.color.text_title),
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            text = userData?.userId ?: "Email Pengguna",
            style = MaterialTheme.typography.labelMedium,
            color = colorResource(id = R.color.text_medium),
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = stringResource(id = R.string.about_me),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            maxLines = 2
        )

        Spacer(modifier = Modifier.weight(1f))

        /*Button(
            onClick = {
                if (userData != null) {
                    onSignOut()
                } else {
                    onSignIn()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.Padding1)
        ) {
            Text(text = if (userData != null) "Logout" else "Login")
        }*/
    }
}

/*
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        navController = rememberNavController(),
        SignInState(),
        onSignInClick = {},
        userData = UserData(userId = "testing", username = "testing", profilePictureUrl = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.vecteezy.com%2Ffree-vector%2Fprofile-icon&psig=AOvVaw1AFV2XhuAwI3YPocw0QhlV&ust=1717465622621000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCIC3hpSovoYDFQAAAAAdAAAAABAJ"),
        onLogoutClick = {})
}*/
