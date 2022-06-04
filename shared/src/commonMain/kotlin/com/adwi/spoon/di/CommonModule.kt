package com.adwi.spoon.di

import com.adwi.spoon.data.local.Database
import com.adwi.spoon.data.remote.service.SpoonService
import com.adwi.spoon.data.remote.service.SpoonServiceImpl
import com.adwi.spoon.data.repository.SpoonRepository
import com.adwi.spoon.data.repository.SpoonRepositoryImpl
import com.adwi.spoon.util.getEnv
import com.squareup.sqldelight.ColumnAdapter
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModule, platformModule())
    }

internal val commonModule = module {
    val baseUrl = getEnv("BASE_URL")
    val apiKey = getEnv("API_KEY")

    single { provideJson() }
    single { provideHttpClient(get(), get(), baseUrl) }
    single<SpoonService> { SpoonServiceImpl(apiKey, get()) }
    single { Database(get(), provideExtendedIngredientsAdapter()) }
    single<SpoonRepository> { SpoonRepositoryImpl(get(), get()) }
}

internal fun provideJson() = Json {
    prettyPrint = true
    isLenient = true
}

internal fun provideHttpClient(engine: HttpClientEngine, json: Json, baseUrl: String): HttpClient {
    return HttpClient(engine) {
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
}

internal fun provideExtendedIngredientsAdapter(): ColumnAdapter<List<String>, String> {
    return object : ColumnAdapter<List<String>, String> {
        override fun decode(databaseValue: String) =
            if (databaseValue.isEmpty()) {
                listOf()
            } else {
                databaseValue.split("$")
            }

        override fun encode(value: List<String>) = value.joinToString(separator = "$")
    }
}