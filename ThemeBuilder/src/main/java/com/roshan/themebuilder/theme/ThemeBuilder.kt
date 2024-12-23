package com.roshan.themebuilder.theme

import com.roshan.themebuilder.data.ColorSchema
import com.roshan.themebuilder.data.ResultState
import com.roshan.themebuilder.toColorInt
import com.roshan.themebuilder.ui.ThemeViewModel
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle


private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

@Composable
private fun getLightColorScheme(themeViewModel: ThemeViewModel): ColorScheme {

    val colorSchemeLight = themeViewModel.colorsLightState.collectAsStateWithLifecycle()

    when(colorSchemeLight.value){
        is ResultState.Failure -> Text(text = "Failed to Load",)
        ResultState.Loading -> LinearProgressIndicator()
        is ResultState.Success -> {
            (colorSchemeLight.value as ResultState.Success<ColorSchema>).data.apply {
                return lightColorScheme(
                    primary = Color(primary.toColorInt()),
                    onPrimary = Color(onPrimary.toColorInt()),
                    primaryContainer = Color(primaryContainer.toColorInt()),
                    onPrimaryContainer = Color(onPrimaryContainer.toColorInt()),
                    secondary = Color(secondary.toColorInt()),
                    onSecondary = Color(onSecondary.toColorInt()),
                    secondaryContainer = Color(secondaryContainer.toColorInt()),
                    onSecondaryContainer = Color(onSecondaryContainer.toColorInt()),
                    tertiary = Color(tertiary.toColorInt()),
                    onTertiary = Color(onTertiary.toColorInt()),
                    tertiaryContainer = Color(tertiaryContainer.toColorInt()),
                    onTertiaryContainer = Color(onTertiaryContainer.toColorInt()),
                    error = Color(error.toColorInt()),
                    onError = Color(onError.toColorInt()),
                    errorContainer = Color(errorContainer.toColorInt()),
                    onErrorContainer = Color(onErrorContainer.toColorInt()),
                    background = Color(background.toColorInt()),
                    onBackground = Color(onBackground.toColorInt()),
                    surface = Color(surface.toColorInt()),
                    onSurface = Color(onSurface.toColorInt()),
                    surfaceVariant = Color(surfaceVariant.toColorInt()),
                    onSurfaceVariant = Color(onSurfaceVariant.toColorInt()),
                    outline = Color(outline.toColorInt()),
                    outlineVariant = Color(outlineVariant.toColorInt()),
                    scrim = Color(scrim.toColorInt()),
                    inverseSurface = Color(inverseSurface.toColorInt()),
                    inverseOnSurface = Color(inverseOnSurface.toColorInt()),
                    inversePrimary = Color(inversePrimary.toColorInt()),
                    surfaceDim = Color(surfaceDim.toColorInt()),
                    surfaceBright = Color(surfaceBright.toColorInt()),
                    surfaceContainerLowest = Color(surfaceContainerLowest.toColorInt()),
                    surfaceContainerLow = Color(surfaceContainerLow.toColorInt()),
                    surfaceContainer = Color(surfaceContainer.toColorInt()),
                    surfaceContainerHigh = Color(surfaceContainerHigh.toColorInt()),
                    surfaceContainerHighest = Color(surfaceContainerHighest.toColorInt()),
                )
            }
        }
    }

    return lightColorScheme(
        primary = primaryLight,
        onPrimary = onPrimaryLight,
        primaryContainer = primaryContainerLight,
        onPrimaryContainer = onPrimaryContainerLight,
        secondary = secondaryLight,
        onSecondary = onSecondaryLight,
        secondaryContainer = secondaryContainerLight,
        onSecondaryContainer = onSecondaryContainerLight,
        tertiary = tertiaryLight,
        onTertiary = onTertiaryLight,
        tertiaryContainer = tertiaryContainerLight,
        onTertiaryContainer = onTertiaryContainerLight,
        error = errorLight,
        onError = onErrorLight,
        errorContainer = errorContainerLight,
        onErrorContainer = onErrorContainerLight,
        background = backgroundLight,
        onBackground = onBackgroundLight,
        surface = surfaceLight,
        onSurface = onSurfaceLight,
        surfaceVariant = surfaceVariantLight,
        onSurfaceVariant = onSurfaceVariantLight,
        outline = outlineLight,
        outlineVariant = outlineVariantLight,
        scrim = scrimLight,
        inverseSurface = inverseSurfaceLight,
        inverseOnSurface = inverseOnSurfaceLight,
        inversePrimary = inversePrimaryLight,
        surfaceDim = surfaceDimLight,
        surfaceBright = surfaceBrightLight,
        surfaceContainerLowest = surfaceContainerLowestLight,
        surfaceContainerLow = surfaceContainerLowLight,
        surfaceContainer = surfaceContainerLight,
        surfaceContainerHigh = surfaceContainerHighLight,
        surfaceContainerHighest = surfaceContainerHighestLight,
    )


}



@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)
// Define a CompositionLocal to hold MyViewModel
val LocalAppViewModel = compositionLocalOf<ThemeViewModel> { error("ThemeViewModel not provided") }
@Composable
fun ThemeBuilder(
    themeViewModel: ThemeViewModel,
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {


//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
////        darkTheme -> darkScheme
//        else -> getLightColorScheme(themeViewModel)
//    }

    MaterialTheme(
        colorScheme = getLightColorScheme(themeViewModel),
        typography = AppTypography,
        content = content
    )
}

