package com.jihan.crypto_tracker

import android.app.Application
import com.jihan.crypto_tracker.crypto.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CryptoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CryptoApplication)
            modules(appModule)
        }

    }
}