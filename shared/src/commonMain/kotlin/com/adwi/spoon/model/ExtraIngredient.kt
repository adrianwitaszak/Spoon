package com.adwi.spoon.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExtendedIngredient(
    @SerialName("amount")
    val amount: Double = 0.0,
    @SerialName("consistency")
    val consistency: String = "",
    @SerialName("image")
    val image: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("original")
    val original: String = "",
    @SerialName("unit")
    val unit: String = ""
)
