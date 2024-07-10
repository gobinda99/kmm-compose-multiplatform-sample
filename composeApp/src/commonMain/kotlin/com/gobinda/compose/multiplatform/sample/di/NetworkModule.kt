package com.gobinda.compose.multiplatform.sample.di

import com.gobinda.compose.multiplatform.sample.data.source.remote.ktor.RestDataSource
import com.gobinda.compose.multiplatform.sample.data.source.remote.ktor.RestDataSourceImpl
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.plugins.resources.Resources
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


@OptIn(ExperimentalSerializationApi::class)
val networkModule = module {

    singleOf(::RestDataSourceImpl){bind<RestDataSource>()}

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
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.v(tag = "Ktor Client", message= message)
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