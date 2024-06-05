package com.ramaa.pmobile_uas.presentation.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ramaa.pmobile_uas.R
import com.ramaa.pmobile_uas.util.Dimens

@Composable
fun LoginScreen(onSignInClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(top = Dimens.Padding1, start = Dimens.Padding1, end = Dimens.Padding1),
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id = R.color.text_title)
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Hai! Selamat datang di Share Spectra!.👋🏻\nYuk login dulu pakai akun Google kamu!",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = Dimens.Padding1)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_app_logo),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(bottom = Dimens.Padding1)
                )
                Spacer(modifier = Modifier.size(Dimens.Padding2))
                Button(onClick = { onSignInClick() }) {
                    Text(
                        text = "Login With Google",
                        style = MaterialTheme.typography.bodyLarge,
                        color = colorResource(id = R.color.text_medium),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}
