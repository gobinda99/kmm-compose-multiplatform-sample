package com.gobinda.compose.multiplatform.sample.data.domain_impl.usecase

import com.gobinda.compose.multiplatform.sample.domain.model.User
import com.gobinda.compose.multiplatform.sample.domain.usecase.LoginUseCase

class LoginUseCaseImpl() : LoginUseCase {

    override suspend fun invoke(email: String, pass: String): User? {
        TODO("Not yet implemented")
    }
}