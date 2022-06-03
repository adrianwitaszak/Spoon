package com.adwi.spoon.data.remote.service

import com.adwi.spoon.model.FoodRecipe
import com.adwi.spoon.model.RecipesResult
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.utils.io.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal class MockHttpClient {

    var recipes = mutableListOf<FoodRecipe>()

    fun initClient(): HttpClient {
        val engine = MockEngine { _ ->
            val recipesResult: RecipesResult = RecipesResult(recipes = recipes)
            val json = Json.encodeToString(recipesResult)
            respond(
                content = ByteReadChannel(json),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val client = HttpClient(engine) {
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
                url("")
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 1000
            }
        }
        return client
    }

    fun teardown() {
        recipes.clear()
    }
}