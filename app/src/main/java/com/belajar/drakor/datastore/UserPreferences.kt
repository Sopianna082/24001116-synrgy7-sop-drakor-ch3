package com.belajar.drakor.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val Context.dataStore by preferencesDataStore("user_preferences")
class UserPreferences(context: Context) {
    private val dataStore = context.dataStore

    val isLoggedIn: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[IS_LOGGED_IN_KEY] ?: false
        }

    val username: Flow<String?> = dataStore.data
        .map { preferences ->
            preferences[USERNAME_KEY]
        }

    suspend fun saveLoginStatus(isLoggedIn: Boolean, username: String?) {
        dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN_KEY] = isLoggedIn
            if (username != null) {
                preferences[USERNAME_KEY] = username
            }
        }
    }

    suspend fun clearLoginStatus() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        private val IS_LOGGED_IN_KEY = booleanPreferencesKey("is_logged_in")
        private val USERNAME_KEY = stringPreferencesKey("username")
    }
}