package com.gobinda.compose.multiplatform.sample.di

import com.gobinda.compose.multiplatform.sample.common.Context
import org.koin.dsl.module

fun appModule(context: Context) = module {
    single <Context>{ context }
    includes(dataModule, factoryModule, vModelModule)
}