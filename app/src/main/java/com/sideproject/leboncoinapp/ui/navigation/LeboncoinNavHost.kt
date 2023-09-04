package com.sideproject.leboncoinapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sideproject.leboncoinapp.ui.detail.DetailScreen
import com.sideproject.leboncoinapp.ui.home.HomeScreen

@Composable
fun LeboncoinNavHost(
    navHostController: NavHostController,
    modifier: Modifier,
    onNavigationEvent: (String) -> Unit,
) {
    NavHost(navController = navHostController, startDestination = Home.route, modifier = modifier) {
        composable(Home.route) {
            HomeScreen(onNavigationEvent)
        }

        composable(
            route = Details.routeWithArgument,
            arguments = listOf(navArgument(Details.argument0) { type = NavType.IntType }),
        ) { backStackEntry ->
            val albumId = backStackEntry.arguments?.getInt(Details.argument0) ?: return@composable

            DetailScreen(albumId)
        }
    }
}
