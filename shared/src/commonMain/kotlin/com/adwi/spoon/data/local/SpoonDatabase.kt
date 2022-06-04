package com.adwi.spoon.data.local

import com.adwi.spoon.RecipeEntity
import com.adwi.spoon.SpoonDatabase
import com.adwi.spoon.data.local.dao.RecipeDao
import com.squareup.sqldelight.ColumnAdapter

internal class Database(
    databaseDriverFactory: DatabaseDriverFactory,
    extendedIngredientsAdapter: ColumnAdapter<List<String>, String>,
) {
    private val database = SpoonDatabase(
        driver = databaseDriverFactory.createDriver(),
        RecipeEntityAdapter = RecipeEntity.Adapter(extendedIngredientsAdapter)
    )
    private val recipeQuery = database.recipeEntityQueries
    private val ingredientQuery = database.ingredientEntityQueries

    val recipeDao: RecipeDao = RecipeDao(recipeQuery, ingredientQuery)

    fun clearDatabase() {
        recipeQuery.transaction {
            recipeQuery.removeAll()
        }
        ingredientQuery.transaction {
            ingredientQuery.removeAll()
        }
    }
}
