package com.adwi.spoon.util


actual fun getEnv(name: String): String {
    return System.getenv(name)
        ?: throw Exception("Can't get $name form environment variables")
}