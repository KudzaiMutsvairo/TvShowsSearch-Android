package com.kudzai.mutswairo.tvshows.feature.tvshow.presentation.navigation

sealed class Screens(val route: String) {
    object SearchScreen : Screens("search_screen")
    object ShowDetailsScreen : Screens("tv_show_details_screen")
}
