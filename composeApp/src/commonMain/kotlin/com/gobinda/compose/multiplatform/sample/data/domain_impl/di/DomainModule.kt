package com.gobinda.compose.multiplatform.sample.data.domain_impl.di


import com.gobinda.compose.multiplatform.sample.data.paging.DogsPagingSourceDatabase
import com.gobinda.compose.multiplatform.sample.data.api.model.DogsPagingSource
import com.gobinda.compose.multiplatform.sample.data.DogsRepository
import com.gobinda.compose.multiplatform.sample.data.domain_impl.usecase.LoginUserUseCaseImpl
import com.gobinda.compose.multiplatform.sample.data.domain_impl.usecase.RegisterUserUserUseCaseImpl
import com.gobinda.compose.multiplatform.sample.data.domain_impl.usecase.UpdateLoggedUserUseCaseImpl
import com.gobinda.compose.multiplatform.sample.data.domain_impl.usecase.GetLoggedUserUseCaseImpl
import com.gobinda.compose.multiplatform.sample.domain.usecase.LoginUserUseCase
import com.gobinda.compose.multiplatform.sample.domain.usecase.RegisterUserUseCase
import com.gobinda.compose.multiplatform.sample.domain.usecase.UpdateLoggedUserUseCase
import com.gobinda.compose.multiplatform.sample.domain.usecase.GetLoggedUserUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {

    factoryOf(::DogsRepository)

    factoryOf(::DogsPagingSource)

    factoryOf(::DogsPagingSourceDatabase)

    factoryOf(::LoginUserUseCaseImpl) {bind<LoginUserUseCase>()}

    factoryOf(::RegisterUserUserUseCaseImpl) {bind<RegisterUserUseCase>()}

    factoryOf(::GetLoggedUserUseCaseImpl) {bind<GetLoggedUserUseCase>()}

    factoryOf(::UpdateLoggedUserUseCaseImpl) {bind<UpdateLoggedUserUseCase>()}

}