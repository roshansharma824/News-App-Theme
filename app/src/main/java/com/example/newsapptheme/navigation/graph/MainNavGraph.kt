package com.example.newsapptheme.navigation.graph

import com.example.newsapptheme.navigation.screen.BottomNavItemScreen
import com.example.newsapptheme.presentation.screens.detail.DetailScreen
import com.example.newsapptheme.presentation.screens.home.HomeScreen
import com.example.newsapptheme.presentation.screens.saved.SavedScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument


@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = BottomNavItemScreen.Saved.route
    ) {
        composable(route = BottomNavItemScreen.Home.route) {
            HomeScreen()
        }
        composable(route = BottomNavItemScreen.Saved.route) {
            SavedScreen(navController = navController)
        }

        composable(route = BottomNavItemScreen.Detail.route, arguments = listOf(navArgument("dataId") {
            type = NavType.StringType
        })) {backStackEntry ->
            val dataId = backStackEntry.arguments?.getString("dataId", "")
            if (dataId != null) {
                DetailScreen(navController = navController,dataId = dataId)
            }

        }

    }
}