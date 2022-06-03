package com.adwi.spoon.data.remote.service

import com.adwi.spoon.model.RecipesResult
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class SpoonServiceImpl(
    private val apiKey: String,
    private val client: HttpClient,
) : SpoonService {

    override suspend fun getRecipes(queries: Map<String, String>): RecipesResult {
        val response = client.get("/recipes/complexSearch") {
            url {
                parameters.append("apiKey", apiKey)
                queries.forEach { query ->
                    parameters.append(query.key, query.value)
                }
            }
        }
        return response.body()
    }
}
