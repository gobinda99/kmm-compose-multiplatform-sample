package com.gobinda.compose.multiplatform.sample.data.d_local.datasource

import com.gobinda.compose.multiplatform.sample.common.Context
import com.gobinda.compose.multiplatform.sample.common.getBoolean
import com.gobinda.compose.multiplatform.sample.common.getData
import com.gobinda.compose.multiplatform.sample.common.putData
import org.koin.core.annotation.Single

@Single
class AppDataStoreImpl(val context: Context) : AppDataStore {
    override suspend fun store(key: String, `object`: String) =
        context.putData(key, `object`)


    override suspend fun store(key: String, `object`: Boolean) =
        context.putData(key, `object`)


    override suspend fun getData(key: String): String? =
        context.getData(key)


    override suspend fun getBoolean(key: String): Boolean? =
        context.getBoolean(key)

}