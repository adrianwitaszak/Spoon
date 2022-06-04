package com.adwi.spoon.data.local

import android.content.Context
import com.adwi.spoon.SpoonDatabase
import com.adwi.spoon.util.Constants
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(SpoonDatabase.Schema, context, Constants.DATABASE_NAME)
    }
}