package com.adwi.spoon.di

import com.adwi.spoon.data.local.Database
import com.adwi.spoon.data.remote.service.SpoonService
import com.adwi.spoon.data.remote.service.SpoonServiceImpl
import com.adwi.spoon.data.repository.SpoonRepository
import com.adwi.spoon.data.repository.SpoonRepositoryImpl
import com.adwi.spoon.util.Constants
import com.squareup.sqldelight.ColumnAdapter
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
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

    single { provideJson() }
    single { provideHttpClient(get()) }
    single<SpoonService> { SpoonServiceImpl(get()) }
    single { Database(get(), provideExtendedIngredientsAdapter()) }
    single<SpoonRepository> { SpoonRepositoryImpl(get(), get()) }
}

internal fun provideJson() = Json {
    prettyPrint = true
    isLenient = true
    ignoreUnknownKeys = true
}

internal fun provideHttpClient(json: Json): HttpClient {
    return HttpClient(CIO) {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(json)
        }
        install(DefaultRequest) {
            headers {
                append(HttpHeaders.Accept, "application/json")
                append("x-api-key", Constants.API_KEY)
            }
            url(Constants.BASE_URL)
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 5000
        }
    }
}

internal fun provideExtendedIngredientsAdapter(): ColumnAdapter<List<String>, String> {
    return object : ColumnAdapter<List<String>, String> {
        override fun decode(databaseValue: String) =
            if (databaseValue.isEmpty()) {
                listOf()
            } else {
                databaseValue.split(",")
            }

        override fun encode(value: List<String>) = value.joinToString(separator = ",")
    }
}