package shvyn22.translationapplication.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import shvyn22.translationapplication.R
import shvyn22.translationapplication.data.local.model.TranslationModel

@Composable
fun HistoryItem(
    translationModel: TranslationModel
) {
    Column(
        modifier = Modifier
            .padding(10.dp)
    ) {
        Text(
            text = translationModel.translateTo,
            maxLines = 1,
            style = MaterialTheme.typography.subtitle1
        )

        Text(
            text = stringResource(id = R.string.text_from, translationModel.text),
            maxLines = 3,
            style = MaterialTheme.typography.subtitle2
        )

        Text(
            text = stringResource(id = R.string.text_to, translationModel.translation),
            maxLines = 3,
            style = MaterialTheme.typography.subtitle2
        )
    }
}