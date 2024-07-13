package com.gobinda.compose.multiplatform.sample.di

import com.gobinda.compose.multiplatform.sample.common.createRoomDatabase
import com.gobinda.compose.multiplatform.sample.data.UserDataSource
import com.gobinda.compose.multiplatform.sample.data.d_local.datasource.AppDataStore
import com.gobinda.compose.multiplatform.sample.data.d_local.datasource.AppDataStoreImpl
import com.gobinda.compose.multiplatform.sample.data.db.AppDatabase
import com.gobinda.compose.multiplatform.sample.data.db.datasource.ModelDataSource
import com.gobinda.compose.multiplatform.sample.data.db.datasource.UserLocalDataSource
import com.gobinda.compose.multiplatform.sample.data.api.model.request.TokenManager
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {

    includes(networkModule)

    single<AppDatabase> { createRoomDatabase(get()) }

    singleOf(::AppDataStoreImpl) { bind<AppDataStore>() }

    singleOf(::UserLocalDataSource) { bind<UserDataSource>() }

    singleOf(::ModelDataSource)

    singleOf(::TokenManager)


}