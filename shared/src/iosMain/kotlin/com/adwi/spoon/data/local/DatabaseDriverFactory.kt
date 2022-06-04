package com.adwi.spoon.data.local

import com.adwi.spoon.SpoonDatabase
import com.adwi.spoon.util.Constants
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(SpoonDatabase.Schema, Constants.DATABASE_NAME)
    }

}