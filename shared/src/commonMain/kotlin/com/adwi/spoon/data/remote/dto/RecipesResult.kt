package com.adwi.spoon.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipesResult(
    @SerialName("results")
    val recipes: List<RecipeDTO> = emptyList(),
)
