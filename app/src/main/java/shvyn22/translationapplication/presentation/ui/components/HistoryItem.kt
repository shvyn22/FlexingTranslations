package shvyn22.translationapplication.presentation.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import shvyn22.translationapplication.R
import shvyn22.translationapplication.data.local.model.TranslationModel

@ExperimentalFoundationApi
@Composable
fun HistoryItem(
    item: TranslationModel,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = item.translateTo,
                maxLines = 1,
                style = MaterialTheme.typography.subtitle1
            )

            Text(
                text = stringResource(id = R.string.text_from, item.text),
                maxLines = 3,
                style = MaterialTheme.typography.subtitle2
            )

            Text(
                text = stringResource(id = R.string.text_to, item.translation),
                maxLines = 3,
                style = MaterialTheme.typography.subtitle2
            )
        }
    }
}