package com.adwi.spoon.data.repository

import co.touchlab.kermit.Logger
import com.adwi.spoon.data.local.Database
import com.adwi.spoon.data.remote.service.SpoonService
import com.adwi.spoon.data.toDomain
import com.adwi.spoon.data.toEntity
import com.adwi.spoon.model.Recipe
import com.adwi.spoon.util.DataState
import com.adwi.spoon.util.networkBoundResource
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SpoonRepositoryImpl(
    private val service: SpoonService,
    database: Database,
) : SpoonRepository {

    private val recipeDao = database.recipeDao
    private val ingredientDao = database.ingredientDao

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getRecipes(
        queries: Map<String, String>,
        forceRefresh: Boolean,
        onFetchSuccess: () -> Unit,
        onFetchRemoteFailed: (Throwable) -> Unit,
    ): Flow<DataState<List<Recipe>>> = networkBoundResource(
        query = {
            val localRecipes = recipeDao.getAll()
            val recipes = localRecipes.map { it.toDomain(ingredientDao) }
            Logger.i { "SpoonRepositoryImpl - getRecipes - query - ${recipes.size}" }
            flowOf(recipes)
        },
        fetch = {
            val response = service.getRecipes(mapOf("query" to "apple"))
            Logger.i { "SpoonRepositoryImpl - getRecipes - fetch - ${response.recipes.size}" }
            response.recipes
        },
        saveFetchResult = { remoteRecipes ->
            val recipes = remoteRecipes.map { dto ->

                val ingredients = dto.extendedIngredientDTOS
                ingredients.forEach {
                    ingredientDao.add(it.toEntity())
                }

                dto.toEntity()
            }

            Logger.i { "SpoonRepositoryImpl - getRecipes - saveFetchRecult - ${recipes.size}" }

            recipes.forEach {
                recipeDao.add(it)
            }
        },
        shouldFetch = { recipes ->
            true
        },
        onFetchSuccess = onFetchSuccess,
        onFetchFailed = { throwable ->
            if (throwable !is IOException && throwable !is IOException) {
                throw throwable
            }
            Logger.i { "SpoonRepositoryImpl - getRecipes - onFetchFailed - ${throwable.message}" }
            onFetchRemoteFailed(throwable)
        }
    )
}