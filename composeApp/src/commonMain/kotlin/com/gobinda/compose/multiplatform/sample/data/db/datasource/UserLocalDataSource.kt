package com.gobinda.compose.multiplatform.sample.data.db.datasource

import com.gobinda.compose.multiplatform.sample.data.db.AppDatabase
import com.gobinda.compose.multiplatform.sample.domain.model.User
import com.gobinda.compose.multiplatform.sample.data.UserDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class UserLocalDataSource (db: AppDatabase) :
    UserDataSource {

    val dispatcher : CoroutineDispatcher = Dispatchers.IO

   val dao = db.userDao()

    override fun getUser(email: String): Flow<Result<User>> {

       return dao.loadUserByEmail(email).map {
            Result.success(it)
        }
    }

    override suspend fun insertUser(user: User) : Long {
        return withContext(dispatcher){
            dao.insertUser(user)
        }
    }

    override suspend fun updateUser(user: User) {
        withContext(dispatcher){
            dao.insertUser(user)
        }
    }

}