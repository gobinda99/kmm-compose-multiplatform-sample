package com.gobinda.compose.multiplatform.sample.data.api.di

import com.gobinda.compose.multiplatform.sample.data.api.datasource.RestDataSource
import com.gobinda.compose.multiplatform.sample.data.api.datasource.RestDataSourceImpl
import com.gobinda.compose.multiplatform.sample.data.api.model.request.Token
import com.gobinda.compose.multiplatform.sample.data.api.model.request.TokenManager
import com.gobinda.compose.multiplatform.sample.di.AppModule
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.plugins.resources.Resources
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.Parameters
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import kotlin.contracts.Returns


@OptIn(ExperimentalSerializationApi::class)
val networkDslModule = module {
    single {
        Json {
            explicitNulls = false
            ignoreUnknownKeys = true
            isLenient = true
            prettyPrint = true
            encodeDefaults = true
            classDiscriminator = "#class"
        }
    }

    single {
        HttpClient {
            expectSuccess = true
            install(DefaultRequest) {
                url {
                    url("https://randomuser.me/")
                    /*parameters.append("key", "value")*/
                }

                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
            install(Resources)
            install(ContentNegotiation) {
                json(get())
            }
            install(HttpCache)
            install(Logging) {
                level = LogLevel.INFO
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.v(tag = "Ktor Client", message = message)
                    }
                }
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        with(get<TokenManager>().token) {
                            BearerTokens(accessToken, refreshToken)
                        }
                    }

                    refreshTokens {
                        with(get<TokenManager>()) {
                            val refreshTokenInfo: Token = client.submitForm(
                                url = "token",
                                formParameters = Parameters.build {
                                    append("grant_type", "refresh_token")
//                                append("client_id", clientId)
                                    append("refresh_token", token.refreshToken)
                                }
                            ) { markAsRefreshTokenRequest() }.body()

                            saveToken(refreshTokenInfo)

                            with(refreshTokenInfo) {
                                BearerTokens(accessToken, refreshToken)
                            }
                        }
                    }
                }
            }
            install(HttpTimeout) {
                val timeout = 60000L
                connectTimeoutMillis = timeout
                requestTimeoutMillis = timeout
                socketTimeoutMillis = timeout
            }
            install(ResponseObserver) {
                onResponse { response ->

                }
            }
            HttpResponseValidator {
                validateResponse { response: HttpResponse ->
                    val statusCode = response.status.value

                }
            }
        }
    }
}

@Module
class NetworkModule {

    /*
    * Add annotation related method add here
    *  */


}
