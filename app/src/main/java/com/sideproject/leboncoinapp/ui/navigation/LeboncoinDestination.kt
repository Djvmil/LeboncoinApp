package com.sideproject.leboncoinapp.ui.navigation

interface LeboncoinDestination {
    val route: String
}

object Home : LeboncoinDestination {
    override val route = "home"
}

object Details : LeboncoinDestination {
    const val routeWithArgument: String = "details/{albumId}"
    const val argument0: String = "albumId"
    override val route = "details"
}
