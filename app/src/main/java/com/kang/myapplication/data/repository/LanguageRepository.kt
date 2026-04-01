package com.kang.myapplication.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

/**
 * Repository responsible for managing user language preferences using Jetpack DataStore.
 */
class LanguageRepository(private val context: Context) {
    private val languageKey = stringPreferencesKey("selected_language")

    /**
     * A flow of the user's selected language. Returns null if no language is selected (All languages).
     */
    val selectedLanguage: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[languageKey]
        }

    /**
     * Persists the user's language selection.
     */
    suspend fun saveLanguage(language: String) {
        context.dataStore.edit { preferences ->
            preferences[languageKey] = language
        }
    }
}
