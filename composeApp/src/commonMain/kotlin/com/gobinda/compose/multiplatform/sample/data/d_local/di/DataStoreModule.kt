package com.gobinda.compose.multiplatform.sample.data.d_local.di

import com.gobinda.compose.multiplatform.sample.data.d_local.datasource.AppDataStore
import com.gobinda.compose.multiplatform.sample.data.d_local.datasource.AppDataStoreImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val dataStoreModule = module {
    singleOf(::AppDataStoreImpl) { bind<AppDataStore>() }
}