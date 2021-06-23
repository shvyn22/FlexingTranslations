package shvyn22.translationapplication.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.map
import shvyn22.translationapplication.data.preferences.PreferencesManager.PreferencesKeys.NIGHT_MODE
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    val nightMode = dataStore.data.map {
        it[NIGHT_MODE] ?: false
    }

    suspend fun editNightMode(newValue: Boolean) = dataStore.edit {
        it[NIGHT_MODE] = newValue
    }

    private object PreferencesKeys {
        val NIGHT_MODE = booleanPreferencesKey("nightMode")
    }
}