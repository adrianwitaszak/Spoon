package com.adwi.spoon.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FoodRecipe(
    @SerialName("aggregateLikes")
    val aggregateLikes: Int = 0,
    @SerialName("cheap")
    val cheap: Boolean = false,
    @SerialName("dairyFree")
    val dairyFree: Boolean = false,
    @SerialName("extendedIngredients")
    val extendedIngredients: List<ExtendedIngredient> = emptyList(),
    @SerialName("glutenFree")
    val glutenFree: Boolean = false,
    @SerialName("id")
    val id: Int = 0,
    @SerialName("image")
    val image: String = "",
    @SerialName("readyInMinutes")
    val readyInMinutes: Int = 0,
    @SerialName("sourceName")
    val sourceName: String = "",
    @SerialName("sourceUrl")
    val sourceUrl: String = "",
    @SerialName("summary")
    val summary: String = "",
    @SerialName("title")
    val title: String = "",
    @SerialName("vegan")
    val vegan: Boolean = false,
    @SerialName("vegetarian")
    val vegetarian: Boolean = false,
    @SerialName("veryHealthy")
    val veryHealthy: Boolean = false,
)
