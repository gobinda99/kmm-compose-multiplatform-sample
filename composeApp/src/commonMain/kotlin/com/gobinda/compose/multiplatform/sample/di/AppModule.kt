package com.gobinda.compose.multiplatform.sample.di

import com.gobinda.compose.multiplatform.sample.common.Context
import com.gobinda.compose.multiplatform.sample.data.api.di.NetworkModule
import com.gobinda.compose.multiplatform.sample.data.api.di.networkModule
import com.gobinda.compose.multiplatform.sample.data.api.model.DogsPagingSource
import com.gobinda.compose.multiplatform.sample.data.d_local.di.dataStoreModule
import com.gobinda.compose.multiplatform.sample.data.db.di.DatabaseModule
import com.gobinda.compose.multiplatform.sample.data.db.di.databaseModule
import com.gobinda.compose.multiplatform.sample.data.domain_impl.di.domainModule
import com.gobinda.compose.multiplatform.sample.presentation.di.vModelModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.koin.mp.KoinPlatformTools
import org.koin.ksp.generated.*

fun appModule(context: Context) = module {
    single<Context> { context }
    includes(AppModule().module)
//    factoryOf(::DogsPagingSource)

}

@Module([DatabaseModule::class, NetworkModule::class])
@ComponentScan("com.gobinda.compose.multiplatform.sample")
class AppModule

