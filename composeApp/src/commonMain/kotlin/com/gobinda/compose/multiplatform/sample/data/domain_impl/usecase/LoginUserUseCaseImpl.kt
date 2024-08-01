package com.gobinda.compose.multiplatform.sample.data.domain_impl.usecase

import com.gobinda.compose.multiplatform.sample.data.db.datasource.UserDataSource
import com.gobinda.compose.multiplatform.sample.domain.model.User
import com.gobinda.compose.multiplatform.sample.domain.usecase.LoginUserUseCase
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class LoginUserUseCaseImpl(private val userDataSource: UserDataSource) : LoginUserUseCase {

    override fun invoke(email: String, pass: String): Flow<Result<User?>> {
       return userDataSource.getUser(email, pass)
    }
}