package com.s3corp.objectdetectionwitharcoredemo.home.navigation

sealed class HomeNavigation(val route: String) {
    companion object {
        private const val HOME_SCREEN_ROUTE = "home_screen"
    }

    data object HomeScreen : HomeNavigation(HOME_SCREEN_ROUTE)
}