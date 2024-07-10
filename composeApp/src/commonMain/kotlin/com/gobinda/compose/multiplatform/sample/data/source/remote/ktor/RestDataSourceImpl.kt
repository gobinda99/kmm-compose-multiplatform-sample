package com.gobinda.compose.multiplatform.sample.data.source.remote.ktor

import com.gobinda.compose.multiplatform.sample.data.source.remote.ktor.response.RandomUser
import com.gobinda.compose.multiplatform.sample.data.source.remote.ktor.response.RandomUserResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.delete
import io.ktor.client.plugins.resources.get
import io.ktor.client.plugins.resources.post
import io.ktor.client.plugins.resources.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow


class RestDataSourceImpl(private val httpClient: HttpClient) : RestDataSource {

    override suspend fun getRandomUser(): RandomUser? {
        return httpClient.get(UserResource()).body<RandomUserResponse>().results.firstOrNull()
    }

    override fun getRandomUserAsFlow(): Flow<RandomUser?> {
      return ::getRandomUser.asFlow()
    }

    override suspend fun postUser(user: RandomUser) : HttpResponse {
        return httpClient.post(UserResource()){
             setBody(user)
         }
    }

    override suspend fun getUserByUserId(id: Long): RandomUser {
        return httpClient.get(UserResource.Id(id = id)).body()
    }

    override suspend fun updateUser(user: RandomUser): HttpResponse {
        return httpClient.put(UserResource.Id(id = 1234)){
            setBody(user)
        }

    }

    override suspend fun deleteUser(id: Long) {
         httpClient.delete(UserResource.Id(id= id))
    }


}




