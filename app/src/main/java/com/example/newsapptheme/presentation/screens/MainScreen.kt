package com.example.newsapptheme.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.newsapptheme.navigation.graph.MainNavGraph
import com.example.newsapptheme.presentation.component.BottomBar


@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            Surface(
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            ) {
                BottomBar(navController = navController)
            }
        },
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            MainNavGraph(navController = navController)
        }
    }

}