package com.adwi.spoon.util

expect fun getEnv(name: String): String

object Constants {
    const val DATABASE_NAME = "spoon.db"
    const val BASE_URL = "https://api.spoonacular.com"
    const val API_KEY = "8cb4f344bc6747a280d478db9924112f"
}