package com.kudzai.mutswairo.tvshows.feature.tvshow.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kudzai.mutswairo.tvshows.feature.tvshow.presentation.search.SearchScreen
import com.kudzai.mutswairo.tvshows.feature.tvshow.presentation.search.TvShowsViewModel
import com.kudzai.mutswairo.tvshows.feature.tvshow.presentation.tvshow.TvShowDetailsEvents
import com.kudzai.mutswairo.tvshows.feature.tvshow.presentation.tvshow.TvShowDetailsScreen
import com.kudzai.mutswairo.tvshows.feature.tvshow.presentation.tvshow.TvShowDetailsViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.SearchScreen.route) {
        composable(route = Screens.SearchScreen.route) {
            val viewModel = hiltViewModel<TvShowsViewModel>()
            val uiState = viewModel.uiState.collectAsStateWithLifecycle()
            SearchScreen(
                uiState = uiState.value,
                onEvent = viewModel::onEvent,
                onNavigateToTvShowEvent = { tvShow ->
                    navController.navigate(Screens.ShowDetailsScreen.route + "/${tvShow.id}")
                },
            )
        }

        composable(
            route = Screens.ShowDetailsScreen.route + "/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType }),
        ) { entry ->
            val viewModel = hiltViewModel<TvShowDetailsViewModel>()
            val uiState = viewModel.uiState.collectAsStateWithLifecycle()
            val showId = entry.arguments?.getLong("id") ?: -1L

            viewModel.onEvent(
                TvShowDetailsEvents.OnInitialize(
                    showId,
                ),
            )
            TvShowDetailsScreen(
                uiState = uiState.value,
                onEvent = viewModel::onEvent,
                onBackNavigation = {
                    navController.popBackStack()
                },
            )
        }
    }
}
