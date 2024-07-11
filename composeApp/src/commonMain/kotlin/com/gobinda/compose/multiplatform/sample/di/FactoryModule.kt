package com.gobinda.compose.multiplatform.sample.di


import com.gobinda.compose.multiplatform.sample.data.source.UserRepository
import com.gobinda.compose.multiplatform.sample.data.source.UserRepositoryImpl
import com.gobinda.compose.multiplatform.sample.data.source.local.room.DogsPagingSourceDatabase
import com.gobinda.compose.multiplatform.sample.data.source.remote.ktor.DogsPagingSource
import com.gobinda.compose.multiplatform.sample.data.source.remote.ktor.DogsRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val factoryModule = module {
    factoryOf(::UserRepositoryImpl) { bind<UserRepository>() }

    factoryOf(::DogsRepository)

    factoryOf(::DogsPagingSource)

    factoryOf(::DogsPagingSourceDatabase)

}