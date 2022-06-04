package com.adwi.spoon.model

@kotlinx.serialization.Serializable
data class Ingredient(
    val name: String = "",
    val image: String = "",
    val amount: Double = 0.0,
    val unit: String = "",
    val consistency: String = "",
    val original: String = "",
)
