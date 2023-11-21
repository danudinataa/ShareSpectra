package com.ramaa.narutowiki.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.ramaa.narutowiki.R
import com.ramaa.narutowiki.navigation.components.AppBottomNavigation
import com.ramaa.narutowiki.navigation.components.BottomNavigationItem
import com.ramaa.narutowiki.presentation.home.HomeScreen
import com.ramaa.narutowiki.presentation.navgraph.Route
import com.ramaa.narutowiki.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigator() {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.baseline_home_24, text = "Home"),
            BottomNavigationItem(icon = R.drawable.baseline_search_24, text = "Search"),
            BottomNavigationItem(icon = R.drawable.baseline_person_24, text = "Profile"),
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.ProfileScreen.route -> 2
        else -> 0
    }

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        AppBottomNavigation(
            items = bottomNavigationItems,
            selectedItem = selectedItem,
            onItemClick = { index ->
                when (index) {
                    0 -> navigateToTab(
                        navController = navController,
                        route = Route.HomeScreen.route
                    )

                    1 -> navigateToTab(
                        navController = navController,
                        route = Route.SearchScreen.route
                    )

                    2 -> navigateToTab(
                        navController = navController,
                        route = Route.ProfileScreen.route
                    )
                }
            }
        )
    }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val character = viewModel.characters.collectAsLazyPagingItems()
                HomeScreen(
                    characters = character,
                    navigate = { navigateToTab(navController = navController, route = it) })
            }
            composable(route = Route.SearchScreen.route) {
            }
            composable(route = Route.DetailsScreen.route) {

            }
            composable(route = Route.ProfileScreen.route) {

            }
        }
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}