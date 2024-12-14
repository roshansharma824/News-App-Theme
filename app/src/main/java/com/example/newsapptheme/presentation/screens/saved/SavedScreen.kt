package com.example.newsapptheme.presentation.screens.saved


import com.example.newsapptheme.navigation.screen.BottomNavItemScreen
import com.example.newsapptheme.presentation.component.NewsCard
import com.example.newsapptheme.presentation.component.ProgressIndicator
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.newsapptheme.R
import com.example.newsapptheme.domain.model.Status


@Composable
fun SavedScreen(
    savedScreenViewModel: SavedScreenViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val data = savedScreenViewModel.newsState.collectAsStateWithLifecycle()

    val tabs = listOf(
        "HOME","ENTERTAINMENT","LIFESTYLE","INDIA","SPORTS","HEALTH","BUSINESS","AUTO","VIRAL","TECHNOLOGY", "CITY"
    )
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    // Remember scroll state to avoid recomposition on scroll
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            Surface(
                shadowElevation = 9.dp,
                ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .background(color = Color.White)
                        ,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Spacer(modifier = Modifier.width(16.dp))
                        IconButton(onClick = {

                        }) {
                            Image(
                                painter = painterResource(id = R.drawable.conscent_logo),
                                contentDescription = "logo"
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Column(
                            modifier = Modifier.padding(start = 4.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center
                        ) {

                        }
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(onClick = {

                        }) {
                            Icon(
                                imageVector = Icons.Outlined.Notifications,
                                contentDescription = "logo"
                            )
                        }
                    }

                    ScrollableTabRow(
                        selectedTabIndex = selectedTabIndex,
                        edgePadding = 1.dp,
                        backgroundColor = Color.White,
                        contentColor = Color.Gray,
                        indicator = { tabPositions ->
                            TabRowDefaults.Indicator(
                                color = Color.Black,
                                modifier = Modifier
                                    .tabIndicatorOffset(tabPositions[selectedTabIndex])
                            )
                        }
                    ) {
                        tabs.forEachIndexed { index, tab ->
                            Tab(
                                selected = selectedTabIndex == index,
                                onClick = { selectedTabIndex = index },
                                modifier = Modifier.padding(horizontal = 8.dp), // Add padding for spacing
                                content = {
                                    Text(
                                        text = tab,
                                        color = if (selectedTabIndex == index) Color.Black else Color.Gray,
                                        fontSize = 14.sp,
                                        fontWeight = if (selectedTabIndex == index) FontWeight.Black else FontWeight.Normal
                                    )
                                }
                            )
                        }
                    }

                }

            }
        },

        ) { paddingValues ->

        when (data.value?.status) {
            Status.LOADING -> {
                ProgressIndicator() // Show loading indicator
            }
            Status.SUCCESS -> {

                when (selectedTabIndex) {
                    0 -> {
                        data.value?.data?.let { newsList ->
                            LazyColumn(
                                contentPadding = paddingValues,
                                modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 0.dp,)
                            ) {
                                itemsIndexed(newsList) { index, newsItem ->
                                    NewsCard(newsItem, index, onItemClicked = {
                                        // Handle item click, using the index if needed
                                        println("Item at index $index clicked")
                                        navController.navigate(BottomNavItemScreen.Detail.passDataId(dataId = "${newsList[index].id}"))
                                    })
                                }
                            }
                        }
                    }
                    1 -> {
                        data.value?.data?.let { newsList ->
                            LazyColumn(
                                contentPadding = paddingValues,
                                modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 0.dp,)
                            ) {
                                itemsIndexed(newsList) { index, newsItem ->
                                    NewsCard(newsItem, index, onItemClicked = {
                                        // Handle item click, using the index if needed
                                        println("Item at index $index clicked")
                                        navController.navigate(BottomNavItemScreen.Detail.passDataId(dataId = "${newsList[index].id}"))
                                    })
                                }
                            }
                        }
                    }
                    2 -> {
                        data.value?.data?.let { newsList ->
                            LazyColumn(
                                contentPadding = paddingValues,
                                modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 0.dp,)
                            ) {
                                itemsIndexed(newsList) { index, newsItem ->
                                    NewsCard(newsItem, index, onItemClicked = {
                                        // Handle item click, using the index if needed
                                        println("Item at index $index clicked")
                                        navController.navigate(BottomNavItemScreen.Detail.passDataId(dataId = "${newsList[index].id}"))
                                    })
                                }
                            }
                        }
                    }
                    3 -> {
                        data.value?.data?.let { newsList ->
                            LazyColumn(
                                contentPadding = paddingValues,
                                modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 0.dp,)
                            ) {
                                itemsIndexed(newsList) { index, newsItem ->
                                    NewsCard(newsItem, index, onItemClicked = {
                                        // Handle item click, using the index if needed
                                        println("Item at index $index clicked")
                                        navController.navigate(BottomNavItemScreen.Detail.passDataId(dataId = "${newsList[index].id}"))
                                    })
                                }
                            }
                        }
                    }
                }
            }
            Status.ERROR -> {
                Text(text = "Failed to load news: ${data.value?.message}")
            }

            null -> {

            }
        }
    }

}
