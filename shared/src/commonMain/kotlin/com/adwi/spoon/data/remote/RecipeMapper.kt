package com.adwi.spoon.data.remote

import com.adwi.spoon.IngredientEntity
import com.adwi.spoon.RecipeEntity
import com.adwi.spoon.data.local.dao.IngredientDao
import com.adwi.spoon.model.Ingredient
import com.adwi.spoon.model.Recipe

fun Recipe.toEntity() = RecipeEntity(
    id = id.toLong(),
    title = title,
    summary = summary,
    sourceName = sourceName,
    sourceUrl = sourceUrl,
    image = image,
    readyInMinutes = readyInMinutes,
    vegan = vegan,
    vegetarian = vegetarian,
    veryHealthy = veryHealthy,
    aggregateLikes = aggregateLikes.toLong(),
    cheap = cheap,
    dairyFree = dairyFree,
    glutenFree = glutenFree,
    extendedIngredients = ingredients.map { it.name }
)

fun RecipeEntity.toDomain(ingredientDao: IngredientDao): Recipe {
    val ingredients = extendedIngredients.map {
        val entity = ingredientDao.getByID(it)
        entity.toDomain()
    }
    return Recipe(
        id = id.toInt(),
        title = title,
        summary = summary,
        sourceName = sourceName,
        sourceUrl = sourceUrl,
        image = image,
        readyInMinutes = readyInMinutes ?: 0,
        vegan = vegan ?: false,
        vegetarian = vegetarian ?: false,
        veryHealthy = veryHealthy ?: false,
        aggregateLikes = aggregateLikes.toInt(),
        cheap = cheap ?: false,
        dairyFree = dairyFree ?: false,
        glutenFree = glutenFree ?: false,
        ingredients = ingredients
    )
}

fun IngredientEntity.toDomain() = Ingredient(
    name = name,
    image = image,
    amount = amount ?: 0.0,
    unit = unit,
    consistency = consistency,
    original = original
)
