package com.example.heatfactory.navigation

sealed class Screen(
    val route: String
) {
    data object ProductMain: Screen(ROUTE_PRODUCT_MAIN)
    data object Favorite: Screen(ROUTE_FAVORITE)
    data object Profile: Screen(ROUTE_PROFILE)
    data object Category: Screen(ROUTE_CATEGORY)
    data object Home: Screen(ROUTE_HOME)

    private companion object {
        const val ROUTE_PRODUCT_MAIN = "product_main"
        const val ROUTE_FAVORITE = "favorite"
        const val ROUTE_PROFILE = "profile"

        const val ROUTE_CATEGORY = "category"
        const val ROUTE_HOME = "home"
    }
}