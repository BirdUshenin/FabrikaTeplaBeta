package com.example.heatfactory.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    homeListScreenContent: @Composable () -> Unit,
    favoriteContent: @Composable () -> Unit,
    profileContent: @Composable () -> Unit,
    categoryContent: @Composable () -> Unit,
){
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ) {
        homeScreenNavGraph(
            homeListScreenContent = homeListScreenContent,
            categoryContent = categoryContent
        )
        composable(Screen.Favorite.route) {
            favoriteContent()
        }
        composable(Screen.Profile.route) {
            profileContent()
        }
    }
}
