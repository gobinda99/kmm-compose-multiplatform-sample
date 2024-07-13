package com.gobinda.compose.multiplatform.sample.data.db.di

import com.gobinda.compose.multiplatform.sample.common.createRoomDatabase
import com.gobinda.compose.multiplatform.sample.data.db.datasource.UserDataSource
import com.gobinda.compose.multiplatform.sample.data.db.AppDatabase
import com.gobinda.compose.multiplatform.sample.data.db.datasource.DogsDataSource
import com.gobinda.compose.multiplatform.sample.data.db.datasource.UserLocalDataSourceImpl
import com.gobinda.compose.multiplatform.sample.data.api.model.request.TokenManager
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val databaseModule = module {

    single<AppDatabase> { createRoomDatabase(get()) }

    singleOf(::UserLocalDataSourceImpl) { bind<UserDataSource>() }

    singleOf(::DogsDataSource)

    singleOf(::TokenManager)


}