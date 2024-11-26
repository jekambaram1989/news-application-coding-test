package com.it.news.presentation.view.main

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.it.news.navigation.HomeNav
import com.it.news.presentation.view.home.HomeViewmodel

@Composable
fun MainScreen(
    navController: NavHostController,
    viewmodel: HomeViewmodel = hiltViewModel()
) {
    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            BottomNav(navController = navController)
        }) { contentPadding ->
        HomeNav(
            navController = navController,
            contentPadding,
            viewmodel
        )
    }
}