package com.example.fitnessandroid.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    primary = appGreen,
    primaryVariant = appGreenVariant,
    onPrimary = white,
    secondary = appGreen,
    secondaryVariant = appGreenVariant,
    onSecondary = white,
    background = appDarkBackground,
    onBackground = appDarkText
)

private val LightColorPalette = lightColors(
    primary = appGreen,
    primaryVariant = appGreenVariant,
    onPrimary = white,
    secondary = appGreen,
    secondaryVariant = appGreenVariant,
    onSecondary = white,
    background = white,
    onBackground = appText
)

/* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
*/

@Composable
fun FitnessAndroidTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}