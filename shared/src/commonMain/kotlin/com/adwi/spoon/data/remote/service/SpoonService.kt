package com.adwi.spoon.data.remote.service

import com.adwi.spoon.model.RecipesResult

interface SpoonService {

    suspend fun getRecipes(queries: Map<String, String>): RecipesResult
}