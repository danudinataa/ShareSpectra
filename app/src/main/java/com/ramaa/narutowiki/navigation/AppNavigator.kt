package com.ramaa.narutowiki.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.ramaa.narutowiki.domain.model.ItemCharacter
import com.ramaa.narutowiki.navigation.components.AppBottomNavigation
import com.ramaa.narutowiki.navigation.components.BottomNavigationItem
import com.ramaa.narutowiki.presentation.bookmark.BookmarkScreen
import com.ramaa.narutowiki.presentation.detail.DetailsScreen
import com.ramaa.narutowiki.presentation.home.HomeScreen
import com.ramaa.narutowiki.presentation.navgraph.Route
import com.ramaa.narutowiki.presentation.profile.ProfileScreen
import com.ramaa.narutowiki.presentation.search.SearchScreen
import com.ramaa.narutowiki.viewmodel.BookmarkViewModel
import com.ramaa.narutowiki.viewmodel.DetailViewModel
import com.ramaa.narutowiki.viewmodel.HomeViewModel
import com.ramaa.narutowiki.viewmodel.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigator() {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.baseline_home_24, text = "Home"),
            BottomNavigationItem(icon = R.drawable.baseline_search_24, text = "Search"),
            BottomNavigationItem(icon = R.drawable.baseline_bookmarks_24, text = "Bookmark"),
            BottomNavigationItem(icon = R.drawable.baseline_person_24, text = "Profile")
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.BookmarkScreen.route -> 2
        else -> 0
    }

    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.SearchScreen.route ||
                backStackState?.destination?.route == Route.BookmarkScreen.route ||
                backStackState?.destination?.route == Route.ProfileScreen.route
    }

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        if (isBottomBarVisible) {
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
                            route = Route.BookmarkScreen.route
                        )

                        3 -> navigateToTab(
                            navController = navController,
                            route = Route.ProfileScreen.route
                        )
                    }
                }
            )
        }
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
                    navigateToSearch = {
                        navigateToTab(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )
                    },
                    navigateToDetails = { char ->
                        navigateToDetails(
                            navController = navController,
                            itemCharacter = char
                        )
                    }
                )
            }
            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                OnBackClickStateSaver(navController = navController)
                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetails = { character ->
                        navigateToDetails(
                            navController = navController,
                            itemCharacter = character
                        )
                    }
                )
            }
            composable(route = Route.DetailsScreen.route) {
                val viewModel: DetailViewModel = hiltViewModel()
                navController.previousBackStackEntry?.savedStateHandle?.get<ItemCharacter?>("character")
                    ?.let { character ->
                        DetailsScreen(
                            itemCharacter = character,
                            event = viewModel::onEvent,
                            navigateUp = { navController.navigateUp() },
                            sideEffect = viewModel.sideEffect
                        )
                    }

            }
            composable(route = Route.BookmarkScreen.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                OnBackClickStateSaver(navController = navController)
                BookmarkScreen(
                    state = state,
                    navigateToDetails = { character ->
                        navigateToDetails(
                            navController = navController,
                            itemCharacter = character
                        )
                    }
                )
            }
            composable(route = Route.ProfileScreen.route) {
                OnBackClickStateSaver(navController = navController)
                ProfileScreen()
            }
        }
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screenRoute ->
            popUpTo(screenRoute) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToDetails(navController: NavController, itemCharacter: ItemCharacter){
    navController.currentBackStackEntry?.savedStateHandle?.set("character", itemCharacter)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}

@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler(true) {
        navigateToTab(
            navController = navController,
            route = Route.HomeScreen.route
        )
    }
}