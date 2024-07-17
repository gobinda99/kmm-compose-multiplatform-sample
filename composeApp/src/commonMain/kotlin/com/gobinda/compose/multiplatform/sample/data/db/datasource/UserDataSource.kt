package com.gobinda.compose.multiplatform.sample.data.db.datasource

import com.gobinda.compose.multiplatform.sample.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserDataSource {

    fun getUser(email : String) : Flow<Result<User?>>

    fun getUser(email : String, pass : String) : Flow<Result<User?>>


    suspend fun insertUser(user : User) : Long

    suspend fun updateUser(user: User)


}