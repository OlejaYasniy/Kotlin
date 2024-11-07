package com.example.kotlin5_6

import android.app.Application
import com.example.kotlin5_6.di.apiModule
import com.example.kotlin5_6.di.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(databaseModule, apiModule))
        }
    }
}
