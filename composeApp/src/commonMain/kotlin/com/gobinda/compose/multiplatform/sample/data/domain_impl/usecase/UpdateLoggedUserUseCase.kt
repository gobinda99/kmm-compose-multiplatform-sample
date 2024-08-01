package com.gobinda.compose.multiplatform.sample.data.domain_impl.usecase

import com.gobinda.compose.multiplatform.sample.data.d_local.datasource.AppDataStore
import com.gobinda.compose.multiplatform.sample.domain.usecase.UpdateLoggedUserUseCase
import org.koin.core.annotation.Factory

@Factory
class UpdateLoggedUserUseCaseImpl(private val dataStore: AppDataStore) : UpdateLoggedUserUseCase {

    override suspend fun invoke(boolean: Boolean) {
        dataStore.store("logIn", true)
    }
}