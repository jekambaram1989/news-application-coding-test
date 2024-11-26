package com.it.news.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.it.news.domain.model.Article
import com.it.news.presentation.view.content.ListItemContentScreen
import com.it.news.presentation.view.home.HomeScreen
import com.it.news.presentation.view.home.HomeViewmodel
import com.it.news.presentation.view.news.NewsScreen

@Composable
fun HomeNav(
    navController: NavController,
    contentPadding: PaddingValues,
    viewmodel: HomeViewmodel
) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController, contentPadding, viewmodel)
        }
        composable(route = Screen.WorldNews.route) {
            NewsScreen(
                navController,
                contentPadding,
                viewmodel
            )
        }
        composable(
            route = Screen.Content.route + "?content={content}",
            arguments = listOf(
                navArgument(
                    name = "content"
                ) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            val contentJsonString = it.arguments?.getString("content")
            val content = Gson().fromJson(contentJsonString, Article::class.java)
            ListItemContentScreen(
                content,
                onClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}