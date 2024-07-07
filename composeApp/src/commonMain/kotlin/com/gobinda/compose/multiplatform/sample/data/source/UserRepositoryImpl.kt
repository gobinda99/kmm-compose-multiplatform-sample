package com.gobinda.compose.multiplatform.sample.data.source

import com.gobinda.compose.multiplatform.sample.data.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
     private val localData: UserDataSource,
     private val remoteData: UserDataSource,
     private val  dispatcher: CoroutineDispatcher = Dispatchers.IO
) : UserRepository {

    override fun getUser(email : String): Flow<Result<User>> {
        return localData.getUser(email)
    }

    override suspend fun insertUser(user: User) : Long {
       return localData.insertUser(user)
    }

    override suspend fun updateUser(user: User) {
        TODO("Not yet implemented")
    }
}