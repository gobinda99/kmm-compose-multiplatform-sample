package com.gobinda.compose.multiplatform.sample.di

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.gobinda.compose.multiplatform.sample.data.source.remote.KtorHttpClient
import com.gobinda.compose.multiplatform.sample.common.Context
import com.gobinda.compose.multiplatform.sample.data.source.UserRepository
import com.gobinda.compose.multiplatform.sample.data.source.UserRepositoryImpl
import com.gobinda.compose.multiplatform.sample.data.source.local.AppDataStore
import com.gobinda.compose.multiplatform.sample.data.source.local.AppDataStoreImpl
import com.gobinda.compose.multiplatform.sample.data.source.local.AppDatabase
import com.gobinda.compose.multiplatform.sample.data.source.local.UserLocalDataSource
import com.gobinda.compose.multiplatform.sample.data.source.local.createRoomDatabase
import com.gobinda.compose.multiplatform.sample.ui.auth.SignInViewModel
import com.gobinda.compose.multiplatform.sample.ui.auth.SignUpViewModel
import kotlinx.serialization.json.Json
import org.koin.dsl.module

fun appModule(context: Context) = module {
    single { Json { isLenient = true; ignoreUnknownKeys = true } }
    single {
        KtorHttpClient.httpClient()
    }
   single<AppDatabase> { createRoomDatabase(context) }
   single <AppDataStore>{ AppDataStoreImpl(context) }

    single<UserRepository> {
        val localData = UserLocalDataSource(get())
        UserRepositoryImpl(localData = localData, remoteData = localData)  }

 /*   single {
        client(get())
    }*/

    factory {SignUpViewModel(get()) }

    factory {SignInViewModel(get(), get()) }

}