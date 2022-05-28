package shvyn22.flexingtranslations.presentation.main

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import kotlinx.coroutines.launch
import shvyn22.flexingtranslations.R
import shvyn22.flexingtranslations.data.local.model.TranslationModel
import shvyn22.flexingtranslations.presentation.ui.components.AppBar
import shvyn22.flexingtranslations.presentation.ui.components.HistoryItem
import shvyn22.flexingtranslations.util.Resource

@ExperimentalFoundationApi
@Composable
fun MainScreen(
    currTranslation: Resource<TranslationModel>,
    historyItems: List<TranslationModel>,
    onToggleTheme: (Boolean) -> Unit,
    onTranslate: (String, String) -> Unit,
    onSetHistoryTranslation: (TranslationModel) -> Unit,
    onDeleteHistoryTranslation: (TranslationModel) -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    val focusManager = LocalFocusManager.current

    var expandedState by remember { mutableStateOf(false) }

    val items = stringArrayResource(id = R.array.translate_to)
    var selectedItem by remember { mutableStateOf(items[0]) }

    var textFieldValue by remember { mutableStateOf("") }
    var translationFieldValue by remember { mutableStateOf("") }

    val isDarkTheme = !MaterialTheme.colors.isLight

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                isDarkTheme = isDarkTheme,
                onToggleTheme = onToggleTheme
            )
        }
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            currTranslation.let { translation ->
                when (translation) {
                    is Resource.Loading -> CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                    is Resource.Success -> {
                        translation.data.let {
                            selectedItem = it.translateTo
                            textFieldValue = it.text
                            translationFieldValue = it.translation
                        }
                    }
                    is Resource.Error -> {
                        translationFieldValue = ""
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(translation.error)
                        }
                    }
                    else -> Unit
                }
            }

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
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
                        text = selectedItem,
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
                                selectedItem = item
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
                        onTranslate(selectedItem, textFieldValue)
                        focusManager.clearFocus()
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

                if (historyItems.isNotEmpty()) {
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
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .constrainAs(lazyHistory) {
                                start.linkTo(parent.start)
                                bottom.linkTo(parent.bottom)
                            }
                    ) {
                        items(historyItems) { item ->
                            HistoryItem(
                                item = item,
                                onClick = { onSetHistoryTranslation(item) },
                                onLongClick = { onDeleteHistoryTranslation(item) }
                            )
                        }
                    }
                }
            }
        }
    }
}