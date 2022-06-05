package com.adwi.spoon.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExtendedIngredientDTO(
    @SerialName("name") val name: String = "",
    @SerialName("image") val image: String = "",
    @SerialName("amount") val amount: Double = 0.0,
    @SerialName("unit") val unit: String = "",
    @SerialName("consistency") val consistency: String = "",
    @SerialName("original") val original: String = "",
)
