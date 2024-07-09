package com.gobinda.compose.multiplatform.sample.di

import com.gobinda.compose.multiplatform.sample.data.source.remote.KtorHttpClient
import com.gobinda.compose.multiplatform.sample.common.Context
import com.gobinda.compose.multiplatform.sample.data.source.UserRepository
import com.gobinda.compose.multiplatform.sample.data.source.UserRepositoryImpl
import com.gobinda.compose.multiplatform.sample.data.source.local.AppDataStore
import com.gobinda.compose.multiplatform.sample.data.source.local.AppDataStoreImpl
import com.gobinda.compose.multiplatform.sample.data.source.local.room.AppDatabase
import com.gobinda.compose.multiplatform.sample.data.source.local.room.UserLocalDataSource
import com.gobinda.compose.multiplatform.sample.common.createRoomDatabase
import com.gobinda.compose.multiplatform.sample.data.source.UserDataSource
import com.gobinda.compose.multiplatform.sample.ui.auth.SignInViewModel
import com.gobinda.compose.multiplatform.sample.ui.auth.SignUpViewModel
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun appModule(context: Context) = module {
    single { Json { isLenient = true; ignoreUnknownKeys = true } }
    single {
        KtorHttpClient.httpClient()
    }
    single <Context>{ context }

    single<AppDatabase> { createRoomDatabase(context) }

    singleOf(::AppDataStoreImpl){ bind<AppDataStore>() }


    singleOf(::UserLocalDataSource) { bind<UserDataSource>() }

    factoryOf(::UserRepositoryImpl) { bind<UserRepository>() }

    viewModelOf(::SignInViewModel)

    viewModelOf(::SignUpViewModel)

}