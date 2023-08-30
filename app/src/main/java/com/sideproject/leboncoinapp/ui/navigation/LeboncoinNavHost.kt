package com.sideproject.leboncoinapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun LeboncoinNavHost(
    navHostController: NavHostController,
    modifier: Modifier,
    isLoadind: Boolean = false,
    onLoadindEvent: (Boolean) -> Unit,
    onNavigationEvent: (LeboncoinDestination) -> Unit,
) {
    NavHost(navController = navHostController, startDestination = Home.route, modifier = Modifier) {
        composable(Home.route){

        }
        composable(Detail.route){

        }
    }
}
