package shvyn22.flexingtranslations.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun TranslationAppTheme(
    isNightMode: Boolean,
    content: @Composable () -> Unit
) {
    val colors = if (isNightMode) DarkColors else LightColors

    MaterialTheme(
        colors = colors,
        shapes = TranslationShapes,
        typography = TranslationTypography,
        content = content
    )
}