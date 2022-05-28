package shvyn22.flexingtranslations.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import shvyn22.flexingtranslations.data.local.model.TranslationModel
import shvyn22.flexingtranslations.data.preferences.PreferencesManager
import shvyn22.flexingtranslations.repository.Repository
import shvyn22.flexingtranslations.util.Resource
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    private val preferences: PreferencesManager
) : ViewModel() {

    val isDarkTheme = preferences.isDarkTheme
        .stateIn(viewModelScope, SharingStarted.Lazily, false)

    private val _currTranslation = MutableStateFlow<Resource<TranslationModel>>(Resource.Idle())
    val currTranslation get() = _currTranslation.asStateFlow()

    val historyItems = flow {
        repository.getHistoryTranslations().collect {
            emit(it)
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun translate(translateTo: String, text: String) {
        viewModelScope.launch {
            repository.translate(translateTo, text).collect {
                _currTranslation.value = it
            }
        }
    }

    fun editThemePreferences(newThemeValue: Boolean) {
        viewModelScope.launch {
            preferences.editThemePreferences(newThemeValue)
        }
    }

    fun setHistoryTranslation(item: TranslationModel) {
        viewModelScope.launch {
            _currTranslation.value = Resource.Success(item)
        }
    }

    fun deleteHistoryTranslation(item: TranslationModel) {
        viewModelScope.launch {
            repository.deleteHistoryTranslation(item)
        }
    }
}