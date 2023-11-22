package com.ramaa.narutowiki.presentation.navgraph

sealed class Route(
    val route: String
) {
    object OnBoardingScreen : Route(route = "onBoardingScreen")

    object HomeScreen : Route(route = "homeScreen")

    object SearchScreen : Route(route = "searchScreen")

    object ProfileScreen : Route(route = "profileScreen")

    object BookmarkScreen : Route(route = "bookmarkScreen")

    object DetailsScreen : Route(route = "detailsScreen")

    object AppStartNavigation : Route(route = "appStartNavigation")

    object AppNavigation : Route(route = "appNavigation")

    object AppNavigatorScreen : Route(route = "appNavigator")
}