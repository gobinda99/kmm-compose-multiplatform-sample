package com.gobinda.compose.multiplatform.sample.di

import com.gobinda.compose.multiplatform.sample.common.Context
import com.gobinda.compose.multiplatform.sample.data.api.di.NetworkModule
import com.gobinda.compose.multiplatform.sample.data.api.di.networkDslModule
import com.gobinda.compose.multiplatform.sample.data.db.di.DatabaseModule
import com.gobinda.compose.multiplatform.sample.data.db.di.databaseDslModule
import com.gobinda.compose.multiplatform.sample.data.domain_impl.di.DomainModule
import com.gobinda.compose.multiplatform.sample.data.domain_impl.di.domainDslModule
import com.gobinda.compose.multiplatform.sample.presentation.di.vModelDlsModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module
import org.koin.ksp.generated.module

fun appModule(context: Context) = module {
    single<Context> { context }
    includes(databaseDslModule, networkDslModule, vModelDlsModule, domainDslModule)
    includes(AppModule().module)
}

@Module([DatabaseModule::class, NetworkModule::class, DomainModule::class])
@ComponentScan("com.gobinda.compose.multiplatform.sample")
class AppModule

