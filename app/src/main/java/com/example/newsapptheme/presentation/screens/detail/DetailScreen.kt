package com.example.newsapptheme.presentation.screens.detail

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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
import com.roshan.themebuilder.ui.text.TextDisplayMediumUi
import com.roshan.themebuilder.ui.text.TextLabelMediumUi
import com.roshan.themebuilder.ui.text.TextTitleLargeUi
import com.roshan.themebuilder.ui.text.TextTitleMediumUi


@Composable
fun DetailScreen(
    detailScreenViewModel: DetailScreenViewModel = hiltViewModel(),
    navController: NavHostController,
    dataId: String
) {
    // Trigger data load
    LaunchedEffect(key1 = dataId) {
        detailScreenViewModel.getNews(dataId)
    }

    // Collect the news state
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


    // Remember LazyListState
    val lazyListState = rememberLazyListState()

    // Track whether the AppBar should be visible
    val shouldShowAppBar = remember { mutableStateOf(true) }

    // Detect scroll direction and update visibility state
    LaunchedEffect(lazyListState) {
        var previousIndex = 0
        var previousScrollOffset = 0

        snapshotFlow { lazyListState.firstVisibleItemIndex to lazyListState.firstVisibleItemScrollOffset }
            .collect { (index, offset) ->
                val isScrollingDown = index > previousIndex ||
                        (index == previousIndex && offset > previousScrollOffset)
                shouldShowAppBar.value = !isScrollingDown
                previousIndex = index
                previousScrollOffset = offset
            }
    }

    // Animate the AppBar's position
    val appBarOffset by animateFloatAsState(
        targetValue = if (shouldShowAppBar.value) 0f else -150f,
        label = "AppBarAnimation"
    )

    Scaffold(
        topBar = {
            Surface(
                modifier = Modifier
                    .graphicsLayer { translationY = appBarOffset }
                    .fillMaxWidth(),

                color = Color.White
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Detail Screen",
                        color = Color.Black,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    ) { paddingValues ->
        when (data?.status) {
            Status.SUCCESS -> {
                LazyColumn(
                    state = lazyListState,
                    contentPadding = PaddingValues(
                        top = paddingValues.calculateTopPadding() ,
                        bottom = paddingValues.calculateBottomPadding()
                    ),
                    modifier = Modifier.fillMaxSize()
                ) {
                    data?.data?.apply {
                        item {
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
                        }
                        item {
                            TextTitleLargeUi(
                                text = title ?: "Title not available",
                                modifier = Modifier.padding(start = 12.dp, end = 12.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        item{
                            Row(
                                modifier = Modifier.padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically,
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
                        }
                        item {
                            TextTitleMediumUi(
                                text = description ?: "Description not available",
                                modifier = Modifier.padding(start = 12.dp, end = 12.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                        items(content ?: emptyList()) { paragraph ->
                            TextBodyMediumUi(
                                text = paragraph,
                                modifier = Modifier.padding(start = 12.dp, end = 12.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }

            Status.ERROR -> {
                Text(
                    text = "Failed to load news: ${data?.message}",
                    modifier = Modifier.padding(16.dp)
                )
            }

            Status.LOADING -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            null -> {}
        }
    }
}
