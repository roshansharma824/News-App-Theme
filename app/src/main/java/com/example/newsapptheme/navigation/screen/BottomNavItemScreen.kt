package com.example.newsapptheme.navigation.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItemScreen(val route: String, val icon: ImageVector, val title: String) {

    data object Home : BottomNavItemScreen("home_screen", Icons.Outlined.Home, "Home")

    data object Saved : BottomNavItemScreen("saved_screen", Icons.Outlined.Favorite, "Saved")

    data object Detail : BottomNavItemScreen("detail_screen/{dataId}", Icons.Outlined.Info, "Detail") {
        fun passDataId(dataId: String): String = "detail_screen/${dataId}"
    }
}
