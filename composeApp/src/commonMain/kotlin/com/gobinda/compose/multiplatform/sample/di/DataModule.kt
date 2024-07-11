package com.gobinda.compose.multiplatform.sample.di

import com.gobinda.compose.multiplatform.sample.common.createRoomDatabase
import com.gobinda.compose.multiplatform.sample.data.source.UserDataSource
import com.gobinda.compose.multiplatform.sample.data.source.local.AppDataStore
import com.gobinda.compose.multiplatform.sample.data.source.local.AppDataStoreImpl
import com.gobinda.compose.multiplatform.sample.data.source.local.room.AppDatabase
import com.gobinda.compose.multiplatform.sample.data.source.local.room.ModelDataSource
import com.gobinda.compose.multiplatform.sample.data.source.local.room.UserLocalDataSource
import com.gobinda.compose.multiplatform.sample.data.source.remote.ktor.DogsRepository
import com.gobinda.compose.multiplatform.sample.data.source.remote.ktor.request.TokenManager
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
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