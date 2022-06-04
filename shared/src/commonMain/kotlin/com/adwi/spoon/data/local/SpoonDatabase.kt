package com.adwi.spoon.data.local

import com.adwi.spoon.RecipeEntity
import com.adwi.spoon.SpoonDatabase
import com.adwi.spoon.data.local.dao.IngredientDao
import com.adwi.spoon.data.local.dao.RecipeDao
import com.squareup.sqldelight.ColumnAdapter

class Database(
    databaseDriverFactory: DatabaseDriverFactory,
    extendedIngredientsAdapter: ColumnAdapter<List<String>, String>,
) {
    private val database = SpoonDatabase(
        driver = databaseDriverFactory.createDriver(),
        RecipeEntityAdapter = RecipeEntity.Adapter(extendedIngredientsAdapter)
    )
    private val recipeQuery = database.recipeEntityQueries
    private val ingredientQuery = database.ingredientEntityQueries

    val recipeDao: RecipeDao = RecipeDao(recipeQuery)
    val ingredientDao: IngredientDao = IngredientDao(ingredientQuery)

    fun clearDatabase() {
        recipeQuery.transaction {
            recipeQuery.removeAll()
        }
        ingredientQuery.transaction {
            ingredientQuery.removeAll()
        }
    }
}
