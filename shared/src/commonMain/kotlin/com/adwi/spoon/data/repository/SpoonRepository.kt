package com.adwi.spoon.data.repository

import com.adwi.spoon.model.Recipe
import com.adwi.spoon.util.DataState
import kotlinx.coroutines.flow.Flow

interface SpoonRepository {

    fun getRecipes(
        queries: Map<String, String>,
        forceRefresh: Boolean,
        onFetchSuccess: () -> Unit,
        onFetchRemoteFailed: (Throwable) -> Unit,
    ): Flow<DataState<List<Recipe>>>
}