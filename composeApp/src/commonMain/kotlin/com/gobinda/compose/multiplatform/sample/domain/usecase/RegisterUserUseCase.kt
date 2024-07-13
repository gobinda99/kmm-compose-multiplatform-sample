package com.gobinda.compose.multiplatform.sample.domain.usecase

import com.gobinda.compose.multiplatform.sample.domain.model.User

interface RegisterUserUseCase {

    suspend operator fun invoke(user: User)
}