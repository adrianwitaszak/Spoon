package com.adwi.spoon.di

import com.adwi.spoon.data.remote.service.SpoonService
import com.adwi.spoon.data.remote.service.SpoonServiceImpl
import com.adwi.spoon.util.getEnv
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val commonModule = module {
    val baseUrl = getEnv("BASE_URL")
    val apiKey = getEnv("API_KEY")

    single { createJson() }
    single { createHttpClient(get(), get(), baseUrl) }
    single<SpoonService> { SpoonServiceImpl(apiKey, get()) }
}

internal fun createJson() = Json {
    prettyPrint = true
    isLenient = true
}

internal fun createHttpClient(engine: HttpClientEngine, json: Json, baseUrl: String): HttpClient =
    HttpClient(engine) {
        install(Logging) {
            level = LogLevel.INFO
        }
        install(ContentNegotiation) {
            json(json)
        }
        install(DefaultRequest) {
            url(baseUrl)
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 1000
        }
    }