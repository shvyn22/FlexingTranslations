package shvyn22.translationapplication.presentation.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.Job
import shvyn22.translationapplication.R

@Composable
fun TranslationAppBar(
    isNightMode: Boolean,
    onToggleMode: (Boolean) -> Job,
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