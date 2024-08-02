package com.gobinda.compose.multiplatform.sample.data.domain_impl.di

import com.gobinda.compose.multiplatform.sample.common.Context
import com.gobinda.compose.multiplatform.sample.common.createRoomDatabase
import com.gobinda.compose.multiplatform.sample.data.db.datasource.UserDataSource
import com.gobinda.compose.multiplatform.sample.data.db.AppDatabase
import com.gobinda.compose.multiplatform.sample.data.db.datasource.DogsDataSource
import com.gobinda.compose.multiplatform.sample.data.db.datasource.UserLocalDataSourceImpl
import com.gobinda.compose.multiplatform.sample.data.api.model.request.TokenManager
import com.gobinda.compose.multiplatform.sample.data.domain_impl.usecase.LoginUserUseCaseImpl
import com.gobinda.compose.multiplatform.sample.domain.usecase.LoginUserUseCase
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainDslModule = module {
    /* Add dsl based koin module declaration here
    * Example : factoryOf(::LoginUserUseCaseImpl) { bind<LoginUserUseCase>()}
    */
}

@Module
class DomainModule {

   /*  Add annotation based Koin function here */
}