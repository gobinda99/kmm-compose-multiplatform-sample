package com.gobinda.compose.multiplatform.sample.data.d_local.datasource

interface AppDataStore {

    suspend fun store(key: String, `object`: String)

    suspend fun getData(key: String): String?

    suspend fun store(key: String, `object`: Boolean)

    suspend fun getBoolean(key: String): Boolean?
}