package com.gobinda.compose.multiplatform.sample.di

import com.gobinda.compose.multiplatform.sample.common.Context
import com.gobinda.compose.multiplatform.sample.data.api.di.networkModule
import com.gobinda.compose.multiplatform.sample.data.d_local.di.dataStoreModule
import com.gobinda.compose.multiplatform.sample.data.db.di.databaseModule
import com.gobinda.compose.multiplatform.sample.data.domain_impl.di.domainModule
import com.gobinda.compose.multiplatform.sample.presentation.di.vModelModule
import org.koin.dsl.module

fun appModule(context: Context) = module {
    single<Context> { context }
    includes(
        networkModule,
        dataStoreModule,
        databaseModule,
        domainModule,
        vModelModule
    )
}