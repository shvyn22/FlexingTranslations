package shvyn22.translationapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import shvyn22.translationapplication.data.local.model.TranslationModel
import shvyn22.translationapplication.data.preferences.PreferencesManager
import shvyn22.translationapplication.repository.Repository
import shvyn22.translationapplication.util.Resource
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    private val preferences: PreferencesManager
) : ViewModel() {

    val nightMode = preferences.nightMode

    private val _currentTranslation = MutableStateFlow<Resource<TranslationModel>>(Resource.Idle())
    val currentTranslation: StateFlow<Resource<TranslationModel>> get() = _currentTranslation

    val historyItems = flow {
        repository.getHistoryItems().collect {
            emit(it)
        }
    }

    fun translate(translateTo: String, text: String) = viewModelScope.launch {
        repository.translate(translateTo, text).collect {
            _currentTranslation.value = it
        }
    }

    fun onToggleMode(newValue: Boolean) = viewModelScope.launch {
        preferences.editNightMode(newValue)
    }
    
    fun onHistoryItemClick(item: TranslationModel) = viewModelScope.launch {
        _currentTranslation.value = Resource.Success(item)
    }

    fun removeFromHistory(item: TranslationModel) = viewModelScope.launch {
        repository.delete(item)
    }
}