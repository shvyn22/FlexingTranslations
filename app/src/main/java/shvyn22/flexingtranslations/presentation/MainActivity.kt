package shvyn22.flexingtranslations.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import shvyn22.flexingtranslations.presentation.main.MainScreen
import shvyn22.flexingtranslations.presentation.main.MainViewModel
import shvyn22.flexingtranslations.presentation.ui.theme.AppTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val systemUiController = rememberSystemUiController()

            val isDarkTheme = viewModel.isDarkTheme.collectAsState()

            AppTheme(
                isDarkTheme = isDarkTheme.value
            ) {
                val statusBarColor = MaterialTheme.colors.primaryVariant

                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = statusBarColor,
                        darkIcons = !isDarkTheme.value
                    )
                }

                val currTranslation = viewModel.currTranslation.collectAsState()
                val historyItems = viewModel.historyItems.collectAsState()

                MainScreen(
                    currTranslation = currTranslation.value,
                    historyItems = historyItems.value,
                    onToggleTheme = viewModel::editThemePreferences,
                    onTranslate = viewModel::translate,
                    onSetHistoryTranslation = viewModel::setHistoryTranslation,
                    onDeleteHistoryTranslation = viewModel::deleteHistoryTranslation
                )
            }
        }
    }
}