package com.gobinda.compose.multiplatform.sample.common

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

internal val prefName = "com.gobinda.compose.multiplatform.sample.common"
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(prefName)

actual suspend fun Context.getData(key: String): String? {
    return dataStore.data.first()[stringPreferencesKey(key)] ?: ""
}

actual suspend fun Context.putData(key: String, `object`: String) {
    dataStore.edit {
        it[stringPreferencesKey(key)] = `object`
    }
}

actual suspend fun Context.putData(
    key: String,
    `object`: Boolean
) {
    dataStore.edit {
        it[booleanPreferencesKey(key)] = `object`
    }
}

actual suspend fun Context.getBoolean(key: String): Boolean? {
    return dataStore.data.first()[booleanPreferencesKey(key)] ?: false

}