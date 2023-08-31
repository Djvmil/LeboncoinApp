package com.sideproject.leboncoinapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sideproject.leboncoinapp.ui.detail.DetailScreen
import com.sideproject.leboncoinapp.ui.home.HomeScreen

@Composable
fun LeboncoinNavHost(
    navHostController: NavHostController,
    modifier: Modifier,
    isLoadind: Boolean = false,
    onLoadindEvent: (Boolean) -> Unit,
    onNavigationEvent: (LeboncoinDestination) -> Unit,
) {
    NavHost(navController = navHostController, startDestination = Home.route, modifier = modifier) {
        composable(Home.route) {
            HomeScreen(
                isLoadind,
                onLoadindEvent,
                onNavigationEvent,
            )
        }

        composable(Detail.route) {
            DetailScreen(
                isLoadind,
                onLoadindEvent,
                onNavigationEvent,
            )
        }
    }
}
