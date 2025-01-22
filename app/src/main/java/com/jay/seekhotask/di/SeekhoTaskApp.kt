package com.jay.seekhotask.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class SeekhoTaskApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SeekhoTaskApp)
            androidLogger(Level.DEBUG)
            modules(module)
        }
    }
}