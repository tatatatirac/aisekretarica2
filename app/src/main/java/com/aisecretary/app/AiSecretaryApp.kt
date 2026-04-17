package com.aisecretary.app

import android.app.Application
import androidx.work.Configuration

class AiSecretaryApp : Application(), Configuration.Provider {
    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder().setMinimumLoggingLevel(android.util.Log.INFO).build()

    override fun onCreate() {
        super.onCreate()
        // Initialize TTS, Crypto
    }
}
