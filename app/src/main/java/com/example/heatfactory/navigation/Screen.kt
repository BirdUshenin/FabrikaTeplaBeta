package com.example.heatfactory.navigation

sealed class Screen(
    val route: String
) {
    data object ProductMain: Screen(ROUTE_PRODUCT_MAIN)
    data object Favorite: Screen(ROUTE_FAVORITE)
    data object Profile: Screen(ROUTE_PROFILE)

    private companion object {
        const val ROUTE_PRODUCT_MAIN = "product_main"
        const val ROUTE_FAVORITE = "favorite"
        const val ROUTE_PROFILE = "profile"
    }
}