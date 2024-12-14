package com.roshan.themebuilder.ui

import com.roshan.themebuilder.data.PlayCard
import com.roshan.themebuilder.data.ResultState
import com.roshan.themebuilder.getSize
import com.roshan.themebuilder.getSoftWrap
import com.roshan.themebuilder.theme.LocalAppViewModel
import com.roshan.themebuilder.toColorInt
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CardUi(
    onClick: () -> Unit,
    modifier: Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    val themeViewModel = LocalAppViewModel.current
    val data = themeViewModel.cardState.collectAsStateWithLifecycle()

    when(data.value){
        is ResultState.Failure -> Text(text = "Failure", modifier= modifier)
        ResultState.Loading -> LinearProgressIndicator()
        is ResultState.Success -> {
            (data.value as ResultState.Success<PlayCard>).data.apply{
                Card(
                    modifier = modifier.padding(
                        start = paddingStart.getSize(),
                        end = paddingEnd.getSize(),
                        top = paddingTop.getSize(),
                        bottom = paddingBottom.getSize()
                    ),
                    onClick = onClick,
                    colors = CardDefaults.cardColors(
                        containerColor = Color(containerColor.toColorInt())
                    ),
                    enabled = enabled.getSoftWrap(),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = elevation.getSize()
                    ),
                    border = BorderStroke(borderWidth.getSize(), Color(borderColor.toColorInt())),
                    shape = RoundedCornerShape(radius.getSize()),
                ) {
                    Column(content = content)
                }
            }

        }
    }


}