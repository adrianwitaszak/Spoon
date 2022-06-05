package com.adwi.spoon

import android.app.Application
import com.adwi.spoon.di.appModule
import com.adwi.spoon.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class SpoonApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@SpoonApplication)
            modules(appModule)
        }
    }
}