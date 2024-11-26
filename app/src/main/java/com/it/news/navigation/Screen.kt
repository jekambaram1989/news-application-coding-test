package com.it.news.navigation

sealed class Screen(val route: String) {
    object Home : Screen("HomeScreen")
    object WorldNews : Screen("WorldNewsScreen")
    object Content : Screen("ContentScreen")
}