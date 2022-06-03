package com.adwi.spoon.util

import kotlinx.cinterop.toKString
import platform.posix.getenv

actual fun getEnv(name: String): String {
    return getenv(name)?.toKString()
        ?: throw NullPointerException("Can't get $name form environment variables")
}