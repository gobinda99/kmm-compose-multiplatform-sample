package com.gobinda.compose.multiplatform.sample.di

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
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.dsl.module

const val KEY = "key"

const val URL_HOST = "www.rijksmuseum.nl"
const val URL_PATH = "api/en/"

@OptIn(ExperimentalSerializationApi::class)
val networkModule = module {
//    singleOf(::KtorRijksMuseumNetwork) { bind<RijksMuseumNetworkDataSource>() }
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
            expectSuccess = false
            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = URL_HOST
                    path(URL_PATH)
                    /*parameters.append(KEY, BuildConfig.API_KEY)*/
                }
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
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
//                    Napier.i("Ktor : ${response.status.value}")
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