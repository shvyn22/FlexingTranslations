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
import shvyn22.flexingtranslations.presentation.ui.theme.TranslationAppTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val nightMode = viewModel.nightMode.collectAsState(initial = false)
            val systemUiController = rememberSystemUiController()

            TranslationAppTheme(isNightMode = nightMode.value) {
                val statusBarColor = MaterialTheme.colors.primaryVariant

                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = statusBarColor,
                        darkIcons = !nightMode.value
                    )
                }

                MainScreen(
                    isNightMode = nightMode.value,
                    onToggleMode = viewModel::onToggleMode,
                    translate = viewModel::translate,
                    currTranslation = viewModel.currentTranslation,
                    historyItems = viewModel.historyItems,
                    onHistoryItemClick = viewModel::onHistoryItemClick,
                    removeFromHistory = viewModel::removeFromHistory
                )
            }
        }
    }
}