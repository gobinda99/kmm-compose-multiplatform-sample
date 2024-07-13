package com.gobinda.compose.multiplatform.sample.data.domain_impl.usecase

import com.gobinda.compose.multiplatform.sample.data.d_local.datasource.AppDataStore
import com.gobinda.compose.multiplatform.sample.domain.usecase.GetLoggedUserUseCase

class GetLoggedUserUseCaseImpl(private val dataStore: AppDataStore) : GetLoggedUserUseCase{

    override suspend fun invoke(): Boolean {
        return dataStore.getBoolean("logIn") ?: false
    }
}