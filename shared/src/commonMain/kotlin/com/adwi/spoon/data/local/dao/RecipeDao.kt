package com.adwi.spoon.data.local.dao

import com.adwi.spoon.RecipeEntity
import com.adwi.spoon.RecipeEntityQueries

class RecipeDao(
    private val recipeEntityQueries: RecipeEntityQueries,
) : CRUDInterface<RecipeEntity> {

    override fun getAll(): List<RecipeEntity> {
        return recipeEntityQueries.getAll().executeAsList()
    }

    override fun getByID(id: String): RecipeEntity {
        return recipeEntityQueries.getById(id.toInt().toLong()).executeAsOne()
    }

    override fun add(item: RecipeEntity) {
        with(item) {
            recipeEntityQueries.add(
                id = id.toInt().toLong(),
                title = title,
                summary = summary,
                sourceName = sourceName,
                sourceUrl = sourceUrl,
                image = image,
                readyInMinutes = readyInMinutes ?: 0,
                vegan = vegan ?: false,
                vegetarian = vegetarian ?: false,
                veryHealthy = veryHealthy ?: false,
                aggregateLikes = aggregateLikes.toInt().toLong(),
                cheap = cheap ?: false,
                dairyFree = dairyFree ?: false,
                glutenFree = glutenFree ?: false,
                extendedIngredients = extendedIngredients
            )
        }
    }

    override fun delete(id: String) {
        recipeEntityQueries.removeById(id.toInt().toLong())
    }
}