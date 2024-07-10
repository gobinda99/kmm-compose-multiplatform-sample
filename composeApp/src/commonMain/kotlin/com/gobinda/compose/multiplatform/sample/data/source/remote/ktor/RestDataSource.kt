package com.gobinda.compose.multiplatform.sample.data.source.remote.ktor

import com.gobinda.compose.multiplatform.sample.data.source.remote.ktor.response.RandomUser
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.flow.Flow

interface RestDataSource {
    suspend fun getRandomUser() : RandomUser?

    fun getRandomUser1() : Flow<RandomUser?>

    suspend fun postUser( user: RandomUser) : HttpResponse

    suspend fun getUserByUserId(id : Long): RandomUser

    suspend fun updateUser(user : RandomUser) :HttpResponse

    suspend fun deleteUser(id : Long)

}