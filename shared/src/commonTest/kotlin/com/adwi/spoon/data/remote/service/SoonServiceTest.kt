package com.adwi.spoon.data.remote.service

import com.adwi.spoon.data.remote.dto.RecipeDTO
import kotlinx.coroutines.runBlocking
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

internal class SpoonServiceTest {

    private lateinit var client: MockHttpClient
    private lateinit var sut: SpoonService

    @BeforeTest
    fun before() {
        client = MockHttpClient()
        sut = SpoonServiceImpl(
            apiKey = "",
            client = client.initClient()
        )
    }

    @AfterTest
    fun teardown() {
        client.teardown()
    }

    @Test
    fun `test Spoon Service -GIVEN list of FoodRecipes WHEN getRecipes SHOULD return the same list`() =
        runBlocking {
            client.recipes.add(RecipeDTO())
            val actual = sut.getRecipes(mapOf("query" to "pasta")).recipes
            assertTrue(actual.size == 1)
        }

    @Test
    fun `test2 Spoon Service -GIVEN list of FoodRecipes WHEN getRecipes SHOULD return the same list`() =
        runBlocking {
            client.recipes.add(RecipeDTO())
            client.recipes.add(RecipeDTO())

            val actual = sut.getRecipes(mapOf("query" to "pasta")).recipes

            assertTrue(actual.size == 2)
        }
}