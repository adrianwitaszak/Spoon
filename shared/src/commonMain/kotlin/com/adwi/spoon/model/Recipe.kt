package com.adwi.spoon.model

@kotlinx.serialization.Serializable
data class Recipe(
    val id: Int = 0,
    val title: String = "",
    val summary: String = "",
    val sourceName: String = "",
    val sourceUrl: String = "",
    val image: String = "",
    val readyInMinutes: Int = 0,
    val vegan: Boolean = false,
    val vegetarian: Boolean = false,
    val veryHealthy: Boolean = false,
    val aggregateLikes: Int = 0,
    val cheap: Boolean = false,
    val dairyFree: Boolean = false,
    val glutenFree: Boolean = false,
    val ingredients: List<Ingredient> = emptyList(),
)
