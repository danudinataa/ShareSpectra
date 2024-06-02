package com.ramaa.pmobile_uas.navgraph

sealed class Route(
    val route: String
) {
    object OnBoardingScreen : Route(route = "onBoardingScreen")

    object HomeScreen : Route(route = "homeScreen")

    object NewsScreen : Route(route = "newsScreen")

    object SearchScreen : Route(route = "searchScreen")

    object ProfileScreen : Route(route = "profileScreen")

    object BookmarkScreen : Route(route = "bookmarkScreen")

    object DetailsScreen : Route(route = "detailsScreen")

    object DetailsCompanyScreen : Route(route = "detailsCompanyScreen")

    object AboutScreen : Route(route = "aboutScreen")

    object AppStartNavigation : Route(route = "appStartNavigation")

    object AppNavigation : Route(route = "appNavigation")

    object AppNavigatorScreen : Route(route = "appNavigator")
}