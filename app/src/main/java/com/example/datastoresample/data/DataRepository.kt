package com.example.datastoresample.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "sample_data_store")

class DataRepository(context: Context) {
    private val dataStore = context.dataStore

    private object PreferenceKey {
        val welcomeKey = booleanPreferencesKey(name = "welcome_complete")
    }

    suspend fun saveWelcomePrefs(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferenceKey.welcomeKey] = completed
        }
    }

    suspend fun readWelcomePrefs() : Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw Exception()
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferenceKey.welcomeKey] ?: false
                onBoardingState
            }
    }
}