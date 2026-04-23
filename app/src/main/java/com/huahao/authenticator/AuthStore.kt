package com.huahao.authenticator

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val AUTH_ENTRIES_KEY = stringPreferencesKey("auth_entries")

class AuthStore(private val dataStore: DataStore<Preferences>) {
    val authEntries: Flow<List<AuthEntry>> = dataStore.data
        .map {
            val entriesJson = it[AUTH_ENTRIES_KEY] ?: "[]"
            Json.decodeFromString<List<AuthEntry>>(entriesJson)
        }

    suspend fun saveAuthEntries(entries: List<AuthEntry>) {
        dataStore.edit {
            it[AUTH_ENTRIES_KEY] = Json.encodeToString(entries)
        }
    }

    suspend fun addAuthEntry(entry: AuthEntry) {
        val entries = authEntries.first()
        saveAuthEntries(entries + entry)
    }

    suspend fun removeAuthEntry(id: String) {
        val entries = authEntries.first()
        saveAuthEntries(entries.filter { it.id != id })
    }
}