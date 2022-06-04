package com.adwi.spoon.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeDTO(
    @SerialName("id") val id: Int = 0,
    @SerialName("title") val title: String = "",
    @SerialName("summary") val summary: String = "",
    @SerialName("sourceName") val sourceName: String = "",
    @SerialName("sourceUrl") val sourceUrl: String = "",
    @SerialName("image") val image: String = "",
    @SerialName("readyInMinutes") val readyInMinutes: Int = 0,
    @SerialName("vegan") val vegan: Boolean = false,
    @SerialName("vegetarian") val vegetarian: Boolean = false,
    @SerialName("veryHealthy") val veryHealthy: Boolean = false,
    @SerialName("aggregateLikes") val aggregateLikes: Int = 0,
    @SerialName("cheap") val cheap: Boolean = false,
    @SerialName("dairyFree") val dairyFree: Boolean = false,
    @SerialName("glutenFree") val glutenFree: Boolean = false,
    @SerialName("extendedIngredients") val extendedIngredientDTOS: List<ExtendedIngredientDTO> = emptyList(),
)
