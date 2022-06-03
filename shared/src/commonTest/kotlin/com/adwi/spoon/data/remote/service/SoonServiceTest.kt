package com.adwi.spoon.data.remote.service

import com.adwi.spoon.model.FoodRecipe
import kotlinx.coroutines.runBlocking
import kotlin.test.*

class SpoonServiceTest {

    private val engine: EngineMock = EngineMock()
    private lateinit var sut: SpoonService

    @BeforeTest
    fun before() {
        sut = SpoonServiceImpl(
            baseURL = "",
            engine = engine.initEngine()
        )
    }

    @AfterTest
    fun teardown() {
        engine.teardown()
    }

    @Test
    fun `test Spoon Service -GIVEN list of FoodRecipes WHEN getRecipes SHOULD return the same list`() =
        runBlocking {
            engine.recipes.add(FoodRecipe())
            val actual = sut.getRecipes(mapOf("query" to "pasta")).recipes
            assertTrue(actual.size == 1)
        }

    @Test
    fun `test2 Spoon Service -GIVEN list of FoodRecipes WHEN getRecipes SHOULD return the same list`() =
        runBlocking {
            engine.recipes.add(FoodRecipe())
            engine.recipes.add(FoodRecipe())

            val actual = sut.getRecipes(mapOf("query" to "pasta")).recipes

            assertTrue(actual.size == 2)
        }
}