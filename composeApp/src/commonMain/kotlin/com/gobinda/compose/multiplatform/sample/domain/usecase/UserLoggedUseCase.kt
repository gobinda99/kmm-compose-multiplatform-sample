package com.gobinda.compose.multiplatform.sample.domain.usecase

interface UserLoggedUseCase {

    suspend operator fun invoke() : Boolean
}