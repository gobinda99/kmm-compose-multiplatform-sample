package com.gobinda.compose.multiplatform.sample.data.source.remote.ktor.request

import com.gobinda.compose.multiplatform.sample.data.source.local.AppDataStore
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TokenManager(val dataStore : AppDataStore) {
    var token : Token = Token()

    val scope = MainScope()

    init {
        runBlocking {
            val accessToken = dataStore.getData("access_token")?: ""
            val refreshToken = dataStore.getData("refresh_token")?: ""
            token = Token(accessToken,refreshToken)
        }
    }

    fun saveToken(newToken : Token){
        token = newToken
        scope.launch {
            dataStore.store("access_token", newToken.accessToken)
            dataStore.store("refresh_token", newToken.refreshToken)
        }
    }
}