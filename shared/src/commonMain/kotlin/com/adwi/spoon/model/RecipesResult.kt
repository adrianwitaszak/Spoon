package com.adwi.spoon.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipesResult(
    @SerialName("results")
    val recipes: List<FoodRecipe> = emptyList()
)
