package com.gobinda.compose.multiplatform.sample.domain.usecase

import com.gobinda.compose.multiplatform.sample.domain.model.User

interface LoginUseCase {

    suspend operator fun invoke(email : String, pass: String) : User?
}