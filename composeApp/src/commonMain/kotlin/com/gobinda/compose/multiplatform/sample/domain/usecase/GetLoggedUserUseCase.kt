package com.gobinda.compose.multiplatform.sample.domain.usecase

interface GetLoggedUserUseCase {

    suspend operator fun invoke() : Boolean
}