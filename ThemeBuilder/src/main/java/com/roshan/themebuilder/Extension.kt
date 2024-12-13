package com.roshan.themebuilder

import com.roshan.themebuilder.theme.provider
import androidx.annotation.ColorInt
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontStyle.Companion.Normal
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

@ColorInt
fun String.toColorInt(value: String = "#ffffff"): Int {
    if (this.isEmpty()) {
        return android.graphics.Color.parseColor(value)
    }
    return android.graphics.Color.parseColor(this)
}

@ColorInt
fun String.toBorderColor(value: String, width: Dp): Color {
    if (width.value.toInt() == 0) {
        return Color.Transparent
    }
    if (this.isEmpty()) {
        return Color(android.graphics.Color.parseColor(value))
    }
    return Color(android.graphics.Color.parseColor(this))
}

fun String.toDefaultOrValue(value: String): String {
    if (this.isEmpty()) {
        return value
    }
    return this
}

fun String.toDefaultOrValuePrice(value: String, price: String): String {
    if (this.isEmpty()) {
        return value + price
    }
    return this + price
}

fun String.getTextAlign(): TextAlign {
    if (this == "left") {
        return TextAlign.Left
    } else if (this == "right") {
        return TextAlign.Right
    } else if (this == "center") {
        return TextAlign.Center
    } else if (this == "justify") {
        return TextAlign.Justify
    }
    return TextAlign.Left
}

fun String.getAlignment(): Alignment {
    if (this == "left") {
        return Alignment.TopStart
    } else if (this == "right") {
        return Alignment.TopEnd
    } else if (this == "center") {
        return Alignment.Center
    } else if (this == "justify") {
        return Alignment.TopCenter
    }
    return Alignment.TopStart
}

fun String.getAlignmentHorizontal(): Alignment.Horizontal? {
    if (this == "left") {
        return Alignment.Start
    } else if (this == "right") {
        return Alignment.End
    } else if (this == "center") {
        return Alignment.CenterHorizontally
    } else if (this == "justify") {
        return Alignment.CenterHorizontally
    }
    return null
}


fun String.getTextDecoration(): TextDecoration {
    if (this == "underline") {
        return TextDecoration.Underline
    } else if (this == "line-through") {
        return TextDecoration.LineThrough
    }
    return TextDecoration.None
}

fun String.getFontWeight(default: String = "500"): FontWeight {
    if (this == "100") {
        return FontWeight.W100
    } else if (this == "200") {
        return FontWeight.W200
    } else if (this == "300") {
        return FontWeight.W300
    } else if (this == "400") {
        return FontWeight.W400
    } else if (this == "500") {
        return FontWeight.W500
    } else if (this == "600") {
        return FontWeight.W600
    } else if (this == "700") {
        return FontWeight.W700
    } else if (this == "800") {
        return FontWeight.W800
    } else if (this == "900") {
        return FontWeight.W900
    }
    if (default == "100") {
        return FontWeight.W100
    } else if (default == "200") {
        return FontWeight.W200
    } else if (default == "300") {
        return FontWeight.W300
    } else if (default == "400") {
        return FontWeight.W400
    } else if (default == "500") {
        return FontWeight.W500
    } else if (default == "600") {
        return FontWeight.W600
    } else if (default == "700") {
        return FontWeight.W700
    } else if (default == "800") {
        return FontWeight.W800
    } else if (default == "900") {
        return FontWeight.W900
    }
    return FontWeight.Normal
}

fun String.getTextWithTransform(value: String): String {
    return when (value) {
        "uppercase" -> {
            this.uppercase(Locale.ROOT)
        }

        "lowercase" -> {
            this.lowercase(Locale.ROOT)
        }

        "capitalize" -> {
            this
                .split(' ')
                .joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }
        }

        else -> this
    }
}

fun String.getSize(): Dp {
    if (this == "") {
        return 0.dp
    }
    return Dp(this.toFloat())
}


fun String.getFontSize(): TextUnit {
    if (this == "") {
        return TextUnit("12".toFloat(), TextUnitType.Sp)
    }

    return TextUnit(this.toFloat(), TextUnitType.Sp)
}

fun String.getTextOverflow(): TextOverflow {
    if (this == "visible") return TextOverflow.Visible
    if (this == "ellipsis") return TextOverflow.Ellipsis
    if (this == "clip") return TextOverflow.Clip
    return TextOverflow.Visible
}

fun String.getFontStyle(): FontStyle {
    if (this == "italic") return Italic
    else return Normal
}

fun String.getMaxLines(): Int {
    if (this == "") {
        return Int.MAX_VALUE
    }
    return this.toInt()
}
fun String.getMinLines(): Int {
    if (this == "") {
        return 1
    }
    return this.toInt()
}
fun String.getLetterSpacing(): TextUnit {
    if (this == "") {
        return TextUnit.Unspecified
    }

    return TextUnit(this.toFloat(), TextUnitType.Sp)
}

fun String.getLineHeight():TextUnit{
    if (this == "") {
        return TextUnit.Unspecified
    }

    return TextUnit(this.toFloat(), TextUnitType.Sp)
}
fun String.getSoftWrap():Boolean{
    return this == ""
}

fun String.getFontFamily(): FontFamily {
    if (this == ""){
        return FontFamily(
            Font(
                googleFont = GoogleFont("Roboto"),
                fontProvider = provider,
            )
        )
    }

    return FontFamily(
        Font(
            googleFont = GoogleFont(this),
            fontProvider = provider,
        )
    )

}


fun spannableText(text: String, spanText: String): AnnotatedString {
    val annotatedString = buildAnnotatedString {
        append(text)
        withStyle(style = SpanStyle(fontSize = 22.sp)) {
            append(spanText)
        }
    }
    return annotatedString
}

fun underLineSpannableText(text: String, spanText: String): AnnotatedString {
    val annotatedString = buildAnnotatedString {
        append(text)
        withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
            append(spanText)
        }
    }
    return annotatedString
}

val SelectionBorderColor = Color(0xFFD0461F)