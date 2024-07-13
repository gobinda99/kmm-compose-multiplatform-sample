package com.gobinda.compose.multiplatform.sample.di


import com.gobinda.compose.multiplatform.sample.domain.usecase.UserRepository
import com.gobinda.compose.multiplatform.sample.data.domain_impl.UserRepositoryImpl
import com.gobinda.compose.multiplatform.sample.data.db.datasource.DogsPagingSourceDatabase
import com.gobinda.compose.multiplatform.sample.data.api.model.DogsPagingSource
import com.gobinda.compose.multiplatform.sample.data.api.model.DogsRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val factoryModule = module {
    factoryOf(::UserRepositoryImpl) { bind<UserRepository>() }

    factoryOf(::DogsRepository)

    factoryOf(::DogsPagingSource)

    factoryOf(::DogsPagingSourceDatabase)

}