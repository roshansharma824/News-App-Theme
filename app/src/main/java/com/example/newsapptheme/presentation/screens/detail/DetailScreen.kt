package com.example.newsapptheme.presentation.screens.detail

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.newsapptheme.R
import com.example.newsapptheme.domain.model.Status
import com.example.newsapptheme.presentation.component.ProgressIndicator
import com.roshan.themebuilder.ui.text.TextBodyMediumUi
import com.roshan.themebuilder.ui.text.TextLabelMediumUi
import com.roshan.themebuilder.ui.text.TextTitleLargeUi
import com.roshan.themebuilder.ui.text.TextTitleMediumUi


@Composable
fun DetailScreen(
    detailScreenViewModel: DetailScreenViewModel = hiltViewModel(),
    navController: NavHostController, dataId: String
) {

    // Get the news when the screen is first composed, no recomposition trigger here
    LaunchedEffect(key1 = dataId) {
        detailScreenViewModel.getNews(dataId)
    }

    // Collecting newsState as a Composable lifecycle-aware state
    val data by detailScreenViewModel.newsState.collectAsStateWithLifecycle()

    // Animations are remembered to avoid recomposition issues
    val isAnimate = remember { mutableStateOf(false) }
    val transition = rememberInfiniteTransition(label = "")
    val rotate by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing)
        ), label = ""
    )

    // Remember scroll state to avoid recomposition on scroll
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            Surface(
                shadowElevation = 9.dp,

                ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Spacer(modifier = Modifier.width(16.dp))
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(
                        modifier = Modifier.padding(start = 4.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {

                    }
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        },

        ) { paddingValues ->

        when (data?.status) {
            Status.SUCCESS -> {
                data?.data?.apply {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .verticalScroll(scrollState)
                    ) {
                        AsyncImage(
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .run { if (isAnimate.value) rotate(rotate) else this },
                            model = urlToImage,
                            contentDescription = description,
                            error = painterResource(R.drawable.ic_launcher_background),
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        TextTitleLargeUi(
                            text = title ?: "Title not available",
                            modifier = Modifier.padding(start = 12.dp, end = 12.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.padding(start = 16.dp),
                            verticalAlignment =  Alignment.CenterVertically,
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.AccountCircle,
                                contentDescription = "",
                                modifier = Modifier
                                    .width(35.dp)
                                    .height(35.dp),
                                tint = Color.Gray
                            )
                            Column {
                                TextLabelMediumUi(
                                    text = "Published: ${publishedAt ?: "N/A"}",
                                    modifier = Modifier.padding(start = 12.dp, end = 12.dp)
                                )

                                TextLabelMediumUi(
                                    text = "By: ${author ?: "Unknown"}",
                                    modifier = Modifier.padding(start = 12.dp, end = 12.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(
                            thickness = 1.dp,
                            color = Color.Gray.copy(alpha = 0.5f)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        TextTitleMediumUi(
                            text = description ?: "Description not available",
                            modifier = Modifier.padding(start = 12.dp, end = 12.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        content?.forEach { it ->
                            TextBodyMediumUi(
                                text = it,
                                modifier = Modifier.padding(start = 12.dp, end = 12.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                    }
                }
            }

            Status.ERROR -> Text(text = "Failed to load news: ${data?.message}")
            Status.LOADING -> ProgressIndicator()
            null -> {}
        }
    }
}
