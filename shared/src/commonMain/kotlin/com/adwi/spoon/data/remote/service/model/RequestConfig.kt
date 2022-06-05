package com.adwi.spoon.data.remote.service.model

data class RequestConfig(
    val method: RequestMethod,
    val path: String,
    val headers: MutableMap<String, String> = mutableMapOf(),
    val query: Map<String, List<String>> = mapOf(),
)

enum class RequestMethod {
    GET, DELETE, HEAD, OPTIONS, PATCH, POST, PUT
}