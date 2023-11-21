package com.ramaa.narutowiki.presentation.navgraph

import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    object OnBoardingScreen : Route(route = "onBoardingScreen")

    object HomeScreen : Route(route = "homeScreen")

    object SearchScreen : Route(route = "searchScreen")

    object ProfileScreen : Route(route = "profileScreen")

    object DetailsScreen : Route(route = "detailsScreen")

    object AppStartNavigation : Route(route = "appStartNavigation")

    object AppNavigation : Route(route = "appNavigation")

    object AppNavigatorScreen : Route(route = "appNavigator")
}