package com.example.newsapptheme.presentation.component


import com.example.newsapptheme.navigation.screen.BottomNavItemScreen
import androidx.compose.foundation.background
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val navigationItems = listOf(
        BottomNavItemScreen.Home,
        BottomNavItemScreen.Saved,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val bottomBarDestination = navigationItems.any { it.route == currentRoute }


    if (bottomBarDestination) {
        BottomNavigation(
            backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor =  MaterialTheme.colorScheme.secondaryContainer,
            modifier = modifier.background(shape = RectangleShape, color = MaterialTheme.colorScheme.secondaryContainer ),
            elevation = 0.dp
        ) {
            navigationItems.forEach { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            tint = if (currentRoute == item.route) {
                                MaterialTheme.colorScheme.primary
                            } else MaterialTheme.colorScheme.secondary
                        )

                    },
                    label = {
                        Text(
                            text = item.title,
                            fontWeight = FontWeight.Normal,
                            color = if (currentRoute == item.route) {
                                MaterialTheme.colorScheme.primary
                            } else MaterialTheme.colorScheme.secondary
                        )
                    },
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.secondary,
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) { saveState = true }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }

        }
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    BottomBar(navController = rememberNavController())
}