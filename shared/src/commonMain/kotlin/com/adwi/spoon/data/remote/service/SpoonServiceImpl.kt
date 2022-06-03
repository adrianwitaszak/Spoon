package com.adwi.spoon.data.remote.service

import com.adwi.spoon.model.RecipesResult
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

const val BASE_URL = "https://api.spoonacular.com"
const val API_KEY = "[YOUR API KEY HERE]"

class SpoonServiceImpl(
    private var baseURL: String = BASE_URL,
    engine: HttpClientEngine,
) : SpoonService {

    private var client = HttpClient(engine) {
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
            url(baseURL)
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 1000
        }
    }

    override suspend fun getRecipes(queries: Map<String, String>): RecipesResult {
        val response = client.get("/recipes/complexSearch") {
            url {
                parameters.append("apiKey", API_KEY)
                queries.forEach { query ->
                    parameters.append(query.key, query.value)
                }
            }
        }
        return response.body()
    }
}