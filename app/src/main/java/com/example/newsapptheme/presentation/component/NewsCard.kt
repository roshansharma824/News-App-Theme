package com.example.newsapptheme.presentation.component


import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.newsapptheme.R
import com.example.newsapptheme.domain.model.News
import com.roshan.themebuilder.ui.CardUi
import com.roshan.themebuilder.ui.text.TextBodyMediumUi
import com.roshan.themebuilder.ui.text.TextDisplayMediumUi
import com.roshan.themebuilder.ui.text.TextHeadlineMediumUi
import com.roshan.themebuilder.ui.text.TextHeadlineSmallUi
import com.roshan.themebuilder.ui.text.TextLabelMediumUi


@Composable
fun NewsCard(item: News, index: Int, onItemClicked: (Int) -> Unit) {

    val isAnimate by remember { mutableStateOf(false) }
    val transition = rememberInfiniteTransition(label = "")
    val rotate by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing)
        ), label = ""
    )


    CardUi(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        onClick = {
//            openUrl(item.url)
            onItemClicked.invoke(index)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            AsyncImage(
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .run { if (isAnimate) rotate(rotate) else this },
                model = item.urlToImage,
                contentDescription = item.description,
                error = painterResource(R.drawable.ic_launcher_background),
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                TextHeadlineMediumUi(
                    modifier = Modifier,
                    text = "${item.title}",
                )
                TextHeadlineSmallUi(
                    modifier = Modifier,
                    text = "${item.description}",
                )
                TextLabelMediumUi(
                    modifier = Modifier,
                    text = "${item.author}",
                )
                TextLabelMediumUi(
                    modifier = Modifier,
                    text = "Published ${item.publishedAt}",
                )
            }
        }
    }


}