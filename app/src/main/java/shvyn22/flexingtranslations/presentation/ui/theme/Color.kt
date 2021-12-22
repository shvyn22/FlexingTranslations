package shvyn22.flexingtranslations.presentation.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val beige50 = Color(0xFFF5F4E2)
val beige200 = Color(0xFFC2C1B0)

val beige700 = Color(0xFF8F7A30)
val beige800 = Color(0xFF5F4E00)
val beige900 = Color(0xFF352600)

val LightColors = lightColors(
    primary = beige50,
    primaryVariant = beige200,
    onPrimary = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    background = beige200,
    onBackground = Color.Black
)

val DarkColors = darkColors(
    primary = beige800,
    primaryVariant = beige900,
    onPrimary = Color.White,
    surface = beige700,
    onSurface = Color.White,
    background = beige900,
    onBackground = Color.White
)