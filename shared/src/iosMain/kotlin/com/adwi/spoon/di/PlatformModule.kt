package com.adwi.spoon.di

import com.adwi.spoon.data.local.DatabaseDriverFactory
import org.koin.dsl.module

actual fun platformModule() = module {
    single { DatabaseDriverFactory() }
}