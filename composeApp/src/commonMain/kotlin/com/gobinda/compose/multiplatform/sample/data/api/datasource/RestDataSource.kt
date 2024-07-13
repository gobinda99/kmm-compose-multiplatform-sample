package com.gobinda.compose.multiplatform.sample.data.api.datasource

import com.gobinda.compose.multiplatform.sample.domain.model.DogsModel
import com.gobinda.compose.multiplatform.sample.data.api.model.response.RandomUser
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.flow.Flow

interface RestDataSource {
    suspend fun getRandomUser() : RandomUser?

    fun getRandomUserAsFlow() : Flow<RandomUser?>

    suspend fun postUser( user: RandomUser) : HttpResponse

    suspend fun getUserByUserId(id : Long): RandomUser

    suspend fun updateUser(user : RandomUser) :HttpResponse

    suspend fun deleteUser(id : Long)


    suspend fun getAllDogs(
      page: Int,
      limit: Int
    ): List<DogsModel>


}