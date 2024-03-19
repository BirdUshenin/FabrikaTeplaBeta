package com.example.heatfactory.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.homeScreenNavGraph(
    homeListScreenContent: @Composable () -> Unit,
    categoryContent: @Composable () -> Unit,
) {
    navigation(
        startDestination = Screen.ProductMain.route,
        route = Screen.Home.route
    ) {
        composable(Screen.ProductMain.route) {
            homeListScreenContent()
        }
        composable(Screen.Category.route) {
            categoryContent()
        }
    }
}