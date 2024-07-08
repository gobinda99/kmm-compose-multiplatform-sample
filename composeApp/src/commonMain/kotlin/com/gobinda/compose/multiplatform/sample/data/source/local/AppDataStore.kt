package com.gobinda.compose.multiplatform.sample.data.source.local

interface AppDataStore {

    suspend fun store(key: String, `object`: String)

    suspend fun getData(key: String): String?

    suspend fun store(key: String, `object`: Boolean)

    suspend fun getBoolean(key: String): Boolean?
}