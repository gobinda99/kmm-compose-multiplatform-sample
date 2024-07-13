package com.gobinda.compose.multiplatform.sample.domain.usecase

interface UpdateLoggedUserUseCase {
     suspend operator fun invoke(setLogin: Boolean)
}