package com.ramaa.pmobile_uas.navgraph

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ramaa.pmobile_uas.presentation.login.LoginScreen
import com.ramaa.pmobile_uas.presentation.navigation.AppNavigator
import com.ramaa.pmobile_uas.presentation.onboarding.OnBoardingEvent
import com.ramaa.pmobile_uas.presentation.onboarding.OnBoardingScreen
import com.ramaa.pmobile_uas.util.Constants
import com.ramaa.pmobile_uas.viewmodel.OnBoardingViewModel
import kotlinx.coroutines.launch

@Composable
fun NavGraph(startDestination: String) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val credentialManager = CredentialManager.create(context)
    val auth: FirebaseAuth = Firebase.auth

    NavHost(navController = navController, startDestination = startDestination) {
        // Tambahkan komposable untuk OnBoardingScreen
        composable(route = Route.OnBoardingScreen.route) {
            OnBoardingScreen(onEvent = { event ->
                // Tambahkan logika navigasi berdasarkan event dari OnBoardingScreen jika diperlukan
                when (event) {
                    OnBoardingEvent.SaveAppEntry -> {
                        // Contoh: navigasi ke layar login setelah menyelesaikan onboarding
                        navController.navigate(Route.LoginScreen.route)
                    }
                }
            })
        }

        composable(route = Route.LoginScreen.route) {
            LoginScreen(onSignInClick = {
                val googleIdOption = GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(true)
                    .setServerClientId(Constants.WEB_ID)
                    .build()
                val request = GetCredentialRequest.Builder()
                    .addCredentialOption(googleIdOption)
                    .build()

                scope.launch {
                    try {
                        val result = credentialManager.getCredential(context = context, request = request)
                        val credential = result.credential
                        val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                        val googleIdToken = googleIdTokenCredential.idToken

                        val firebaseCredential = GoogleAuthProvider.getCredential(googleIdToken, null)

                        auth.signInWithCredential(firebaseCredential)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    navController.popBackStack()
                                    navController.navigate(Route.AppNavigatorScreen.route)
                                } else {
                                    Toast.makeText(context, "Sign In Failed!", Toast.LENGTH_SHORT).show()
                                }
                            }
                    } catch (e: Exception) {
                        Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }

        navigation(route = Route.AppNavigation.route, startDestination = Route.AppNavigatorScreen.route) {
            composable(route = Route.AppNavigatorScreen.route) {
                AppNavigator()
            }
        }
    }
}
