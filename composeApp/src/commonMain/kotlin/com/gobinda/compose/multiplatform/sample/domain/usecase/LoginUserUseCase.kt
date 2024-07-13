package com.gobinda.compose.multiplatform.sample.domain.usecase

import com.gobinda.compose.multiplatform.sample.domain.model.User
import kotlinx.coroutines.flow.Flow

interface LoginUserUseCase {

    operator fun invoke(email : String, pass: String) : Flow<Result<User?>>
}