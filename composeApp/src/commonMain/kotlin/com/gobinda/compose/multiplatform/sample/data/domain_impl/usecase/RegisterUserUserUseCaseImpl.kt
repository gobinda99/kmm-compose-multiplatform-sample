package com.gobinda.compose.multiplatform.sample.data.domain_impl.usecase

import com.gobinda.compose.multiplatform.sample.data.db.datasource.UserDataSource
import com.gobinda.compose.multiplatform.sample.domain.model.User
import com.gobinda.compose.multiplatform.sample.domain.usecase.RegisterUserUseCase
import org.koin.core.annotation.Factory

@Factory
class RegisterUserUserUseCaseImpl(private val userDataSource: UserDataSource) : RegisterUserUseCase{

    override suspend operator fun invoke(user: User) {
        userDataSource.insertUser(user)
    }
}