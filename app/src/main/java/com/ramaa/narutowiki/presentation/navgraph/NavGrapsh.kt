package com.ramaa.narutowiki.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.ramaa.narutowiki.navigation.AppNavigator
import com.ramaa.narutowiki.presentation.onboarding.OnBoardingScreen
import com.ramaa.narutowiki.viewmodel.OnBoardingViewModel

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(route = Route.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(onEvent = viewModel::onEvent)
            }
        }

        navigation(
            route = Route.AppNavigation.route,
            startDestination = Route.AppNavigatorScreen.route
        ) {
            composable(route = Route.AppNavigatorScreen.route){
                AppNavigator()
            }
        }
    }
}