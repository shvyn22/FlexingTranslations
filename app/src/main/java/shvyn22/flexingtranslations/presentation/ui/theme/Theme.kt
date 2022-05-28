package shvyn22.flexingtranslations.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(
    isDarkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colors = if (isDarkTheme) DarkColors else LightColors

    MaterialTheme(
        colors = colors,
        shapes = AppShapes,
        typography = AppTypography,
        content = content
    )
}