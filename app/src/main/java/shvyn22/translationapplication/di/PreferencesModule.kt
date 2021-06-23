package shvyn22.translationapplication.di

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import shvyn22.translationapplication.util.PREFERENCES_FILE_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Singleton
    @Provides
    fun provideDataStore(app: Application): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = {
                app.preferencesDataStoreFile(PREFERENCES_FILE_NAME)
            }
        )
}