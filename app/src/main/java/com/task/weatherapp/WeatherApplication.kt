package com.task.weatherapp

import android.app.Application
import com.task.weatherapp.common.di.mainModule
import com.task.weatherapp.common.di.networkingModule
import com.task.weatherapp.common.di.weatherModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class WeatherApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())


        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@WeatherApplication)
            modules(networkingModule, mainModule, weatherModule)
        }
    }
}