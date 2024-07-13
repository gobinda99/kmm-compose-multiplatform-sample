package com.gobinda.compose.multiplatform.sample.domain.usecase

import com.gobinda.compose.multiplatform.sample.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUser(email : String) : Flow<Result<User>>

    suspend fun insertUser(user : User) : Long

    suspend fun updateUser(user: User)



}