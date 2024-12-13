package com.roshan.themebuilder.ui.text

import com.roshan.themebuilder.data.PlayText
import com.roshan.themebuilder.data.ResultState
import com.roshan.themebuilder.getFontFamily
import com.roshan.themebuilder.getFontSize
import com.roshan.themebuilder.getFontStyle
import com.roshan.themebuilder.getFontWeight
import com.roshan.themebuilder.getLetterSpacing
import com.roshan.themebuilder.getLineHeight
import com.roshan.themebuilder.getMaxLines
import com.roshan.themebuilder.getMinLines
import com.roshan.themebuilder.getSize
import com.roshan.themebuilder.getSoftWrap
import com.roshan.themebuilder.getTextAlign
import com.roshan.themebuilder.getTextDecoration
import com.roshan.themebuilder.getTextOverflow
import com.roshan.themebuilder.getTextWithTransform
import com.roshan.themebuilder.theme.LocalAppViewModel
import com.roshan.themebuilder.toColorInt
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun TextHeadlineSmallUi(
    modifier: Modifier = Modifier,

    text: String,
) {
    val themeViewModel = LocalAppViewModel.current
    val data = themeViewModel.textHeadlineSmallState.collectAsStateWithLifecycle()

    when (data.value) {
        is ResultState.Failure -> Text(text = text, modifier= modifier)
        ResultState.Loading -> LinearProgressIndicator()
        is ResultState.Success -> {
            (data.value as ResultState.Success<PlayText>).data.apply {
                Text(
                    text = text.getTextWithTransform(textTransform),
                    color = Color(color.toColorInt()),
                    fontWeight = fontWeight.getFontWeight(),
                    textAlign = textAlign.getTextAlign(),
                    fontSize = fontSize.getFontSize(),
                    maxLines = maxLines.getMaxLines(),
                    overflow = overflow.getTextOverflow(),
                    fontStyle = fontStyle.getFontStyle(),
                    textDecoration = textDecoration.getTextDecoration(),
                    fontFamily = fontFamily.getFontFamily(),
                    minLines = minLines.getMinLines(),
                    letterSpacing = letterSpacing.getLetterSpacing(),
                    lineHeight = lineHeight.getLineHeight(),
                    softWrap = softWrap.getSoftWrap(),
                    modifier = modifier.padding(
                        start = paddingStart.getSize(),
                        end = paddingEnd.getSize(),
                        top = paddingTop.getSize(),
                        bottom = paddingBottom.getSize()
                    )
                )
            }
        }
    }
}
