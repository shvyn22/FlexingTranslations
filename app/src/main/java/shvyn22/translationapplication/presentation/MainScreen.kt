package shvyn22.translationapplication.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import shvyn22.translationapplication.R
import shvyn22.translationapplication.data.local.model.TranslationModel
import shvyn22.translationapplication.presentation.ui.components.HistoryItem
import shvyn22.translationapplication.presentation.ui.components.TranslationAppBar
import shvyn22.translationapplication.util.Resource

@Composable
fun MainScreen(
    isNightMode: Boolean,
    onToggleMode: (Boolean) -> Job,
    translate: (String, String) -> Job,
    currTranslation: StateFlow<Resource<TranslationModel>>,
    historyItems: Flow<List<TranslationModel>>
) {
    var expandedState by remember { mutableStateOf(false) }

    val items = stringArrayResource(id = R.array.translate_to)
    var selectedItemState by remember { mutableStateOf(items[0]) }

    var textFieldValue by remember { mutableStateOf("") }
    var translationFieldValue by remember { mutableStateOf("") }

    val currTranslationState = currTranslation.collectAsState(initial = Resource.Idle())
    val historyItemsState = historyItems.collectAsState(initial = listOf())

    Scaffold(
        topBar = {
            TranslationAppBar(
                isNightMode = isNightMode,
                onToggleMode = onToggleMode
            )
        }
    ) {
        Box {
            currTranslationState.value.let { translation ->
                if (translation is Resource.Loading) CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
                else if (translation is Resource.Success) translationFieldValue =
                    translation.data.translation
            }

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                val (
                    selectedItemRow,
                    dropDownMenu,
                    textField,
                    translationField,
                    buttonTranslate,
                    historyText,
                    lazyHistory
                ) = createRefs()

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colors.surface,
                            shape = MaterialTheme.shapes.small
                        )
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            expandedState = true
                        }
                        .constrainAs(selectedItemRow) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        }
                ) {
                    Text(
                        text = selectedItemState,
                        modifier = Modifier
                    )

                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier
                    )
                }

                DropdownMenu(
                    expanded = expandedState,
                    onDismissRequest = { expandedState = false },
                    modifier = Modifier
                        .constrainAs(dropDownMenu) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                        }
                ) {
                    items.forEach { item ->
                        DropdownMenuItem(
                            onClick = {
                                selectedItemState = item
                                expandedState = false
                            }
                        ) {
                            Text(
                                text = item
                            )
                        }
                    }
                }


                OutlinedTextField(
                    value = textFieldValue,
                    onValueChange = { textFieldValue = it },
                    maxLines = 5,
                    label = {
                        Text(
                            text = stringResource(id = R.string.hint_text)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                        .constrainAs(textField) {
                            top.linkTo(selectedItemRow.bottom)
                            start.linkTo(parent.start)
                        }
                )

                Button(
                    onClick = {
                        translate(selectedItemState, textFieldValue)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                        .constrainAs(buttonTranslate) {
                            top.linkTo(textField.bottom)
                            start.linkTo(parent.start)
                        }
                ) {
                    Text(
                        text = stringResource(id = R.string.text_translate)
                    )
                }

                OutlinedTextField(
                    value = translationFieldValue,
                    onValueChange = { },
                    maxLines = 5,
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                        .constrainAs(translationField) {
                            top.linkTo(buttonTranslate.bottom)
                            start.linkTo(parent.start)
                        }
                )

                if (historyItemsState.value.isNotEmpty()) {
                    Text(
                        text = stringResource(id = R.string.text_history),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .constrainAs(historyText) {
                                bottom.linkTo(lazyHistory.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    )
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .constrainAs(lazyHistory) {
                                start.linkTo(parent.start)
                                bottom.linkTo(parent.bottom)
                            }
                    ) {
                        items(historyItemsState.value) {
                            HistoryItem(translationModel = it)
                        }
                    }
                }
            }
        }
    }
}