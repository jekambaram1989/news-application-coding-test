package com.it.news.presentation.view.main

import androidx.compose.material.BottomNavigation
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.it.news.presentation.util.BottomNavItems.Companion.navItems
import com.it.news.ui.theme.blue

@Composable
fun BottomNav(navController: NavHostController) {
    BottomNavigation(
        elevation = 1.dp,
        contentColor = Color.White,
        backgroundColor = Color.White,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination?.route
        navItems.forEach { navItem ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = blue,
                    disabledIconColor = Color.Gray,
                    disabledTextColor = Color.Gray,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.Black
                ),
                selected = currentDestination == navItem.route,
                label = {
                    Text(text = navItem.title)
                },
                onClick = {
                    navController.navigate(navItem.route) {
                        navController.graph.startDestinationRoute?.let { screenRoute ->
                            popUpTo(screenRoute)
                        }
                    }
                },
                icon = {
                    Icon(imageVector = navItem.icon, contentDescription = navItem.title)
                })
        }
    }
}