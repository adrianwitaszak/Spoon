package com.adwi.spoon.data.remote.service

import co.touchlab.kermit.Logger
import com.adwi.spoon.data.remote.model.RecipesResult
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

internal class SpoonServiceImpl(
    private val client: HttpClient,
) : SpoonService {

    override suspend fun getRecipes(queries: Map<String, String>): RecipesResult {
        val response = client.get("recipes/complexSearch") {
            url {
                queries.forEach { query ->
                    parameters.append(name = query.key, value = query.value)
                }
            }
            Logger.i { "SpoonServiceImpl - getRecipes - \n\nurl = ${url.host}, \nurlPath = ${url.encodedPath},\nurlParameters = ${url.parameters}\n\n" }
        }
        return response.body()
    }
}
