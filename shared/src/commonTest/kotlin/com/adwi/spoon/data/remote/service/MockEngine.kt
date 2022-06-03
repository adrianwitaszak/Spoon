package com.adwi.spoon.data.remote.service

import com.adwi.spoon.model.FoodRecipe
import com.adwi.spoon.model.RecipesResult
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class EngineMock {

    var recipes = mutableListOf<FoodRecipe>()

    fun initEngine() = MockEngine { _ ->
        val recipesResult: RecipesResult = RecipesResult(recipes = recipes)
        val json = Json.encodeToString(recipesResult)
        respond(
            content = ByteReadChannel(json),
            status = HttpStatusCode.OK,
            headers = headersOf(HttpHeaders.ContentType, "application/json")
        )
    }

    fun teardown() {
        recipes.clear()
    }
}