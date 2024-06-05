package com.ramaa.pmobile_uas.presentation.profile

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.FilledIconButton
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.ramaa.pmobile_uas.R
import com.ramaa.pmobile_uas.navgraph.Route
import com.ramaa.pmobile_uas.presentation.login.UserData
import com.ramaa.pmobile_uas.ui.theme.LightGreen
import com.ramaa.pmobile_uas.util.Dimens

@Composable
fun ProfileScreen(
    navController: NavController,
    currentUser: FirebaseUser?,
    onSignOutClick: () -> Unit
) {
    Log.d("ProfileScreen", "currentUser: ${currentUser?.email}")
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

        currentUser?.let { user ->
            user.photoUrl?.let {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(it)
                        .build(),
                    contentDescription = "Profile Picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(200.dp)
                        .border(2.dp, LightGreen, CircleShape)
                )
            }
            user.displayName?.let { name ->
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleLarge,
                    color = colorResource(id = R.color.text_title),
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
            user.email?.let { mailId ->
                Text(
                    text = mailId,
                    style = MaterialTheme.typography.labelMedium,
                    color = colorResource(id = R.color.text_medium),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        } ?: run {
            Log.e("ProfileScreen", "currentUser is null")
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = stringResource(id = R.string.about_me),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            maxLines = 2
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { onSignOutClick() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.Padding1)
        ) {
            Text(text = "Logout")
        }
    }
}
