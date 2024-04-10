package com.s3corp.objectdetectionwitharcoredemo.detail.navigation

import com.s3corp.objectdetectionwitharcoredemo.home.navigation.HomeNavigation

sealed class DetailNavigation(val route: String) {
    companion object {
        private const val DETAIL_SCREEN_ROUTE = "detail_screen"
    }

    data object DetailScreen : DetailNavigation(DETAIL_SCREEN_ROUTE)
}