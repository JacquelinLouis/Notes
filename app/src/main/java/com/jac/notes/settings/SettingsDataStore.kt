package com.jac.notes.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

class SettingsDataStore(context: Context) {

    data class Setting<T>(val key: Preferences.Key<T>, val title: String, val description: String, val value: T)

    private object Key {
        val LOGGING = booleanPreferencesKey("LOGGING")
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")

    private val contextDataStore = context.dataStore

    private val initSettings = listOf(
        Setting(Key.LOGGING, "Enable logging", "Enable or disable login on application startup", false)
    )

    val settings: Flow<List<Setting<*>>> = context.dataStore.data.transform {
        val settings = mutableListOf<Setting<*>>()
        for (setting in initSettings) {
            settings.add(setting.copy(value = it[setting.key] ?: false))
        }
        emit(settings)
    }

    suspend fun <T> setSetting(setting: Setting<T>) {
        contextDataStore.edit {
            when (setting.value) {
                is Boolean -> it[setting.key] = setting.value
            }
        }
    }
}