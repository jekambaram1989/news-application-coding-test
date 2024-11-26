package com.it.news.presentation.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Place
import androidx.compose.ui.graphics.vector.ImageVector
import com.it.news.navigation.Screen

data class BottomNavItems(
    val title: String, val route: String, val icon: ImageVector
) {
    companion object {
        val navItems = listOf(
            BottomNavItems("Headlines", Screen.Home.route, Icons.Rounded.Home),
            BottomNavItems("News", Screen.WorldNews.route, Icons.Rounded.Place)
        )
    }
}