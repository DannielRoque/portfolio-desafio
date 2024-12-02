package com

import android.app.Application
import com.example.core.di.coreModule
import com.example.feednoticias.di.feedModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApplicationApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ApplicationApp)
            modules(listOf(coreModule, feedModule))
        }
    }
}