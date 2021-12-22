package shvyn22.flexingtranslations.presentation.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import shvyn22.flexingtranslations.R

@Composable
fun TranslationAppBar(
    isNightMode: Boolean,
    onToggleMode: (Boolean) -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name)
            )
        },
        actions = {
            IconButton(
                onClick = { onToggleMode(!isNightMode) }
            ) {
                Icon(
                    imageVector = if (isNightMode) Icons.Filled.LightMode
                    else Icons.Filled.DarkMode,
                    contentDescription = null
                )
            }
        }
    )
}