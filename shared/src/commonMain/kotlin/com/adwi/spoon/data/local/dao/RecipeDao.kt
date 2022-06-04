package com.adwi.spoon.data.local.dao

import com.adwi.spoon.IngredientEntityQueries
import com.adwi.spoon.RecipeEntityQueries
import com.adwi.spoon.data.remote.toDomain
import com.adwi.spoon.data.remote.toEntity
import com.adwi.spoon.model.Recipe

internal class RecipeDao(
    private val recipeEntityQueries: RecipeEntityQueries,
    ingredientEntityQueries: IngredientEntityQueries,
) : CRUDInterface<Recipe> {

    private val ingredientDao: IngredientDao = IngredientDao(ingredientEntityQueries)

    override fun getAll(): List<Recipe> {
        val entities = recipeEntityQueries.getAll().executeAsList()
        return entities.map { it.toDomain(ingredientDao) }
    }

    override fun getByID(id: String): Recipe {
        val entity = recipeEntityQueries.getById(id.toInt().toLong()).executeAsOne()
        return entity.toDomain(ingredientDao)
    }

    override fun add(item: Recipe) {
        item.toEntity()
    }

    override fun update(item: Recipe) {
        item.toEntity()
    }

    override fun delete(id: String) {
        recipeEntityQueries.removeById(id.toInt().toLong())
    }
}