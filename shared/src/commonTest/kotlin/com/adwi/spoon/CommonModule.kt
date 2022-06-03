package com.adwi.spoon

import com.adwi.spoon.data.remote.service.SpoonService
import com.adwi.spoon.data.remote.service.SpoonServiceImpl
import com.adwi.spoon.util.getEnv
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val commonModule = module {
    val baseUrl = getEnv("BASE_URL")
    val apiKey = getEnv("API_KEY")

    val httpClient = HttpClient(CIO) {
        install(Logging) {
            level = LogLevel.INFO
        }
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
        install(DefaultRequest) {
            url(baseUrl)
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 1000
        }
    }

    single<SpoonService> { SpoonServiceImpl(apiKey, get()) }
    single { httpClient }
}