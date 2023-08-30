package com.sideproject.leboncoinapp.ui.navigation

interface LeboncoinDestination {
    val route: String
}

object Home : LeboncoinDestination {
    override val route = "home"
}

object Detail : LeboncoinDestination {
    override val route = "detail"
}
