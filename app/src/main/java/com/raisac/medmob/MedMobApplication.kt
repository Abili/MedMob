package com.raisac.medmob

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Main Application class for MedMob Doctors App
 * Configured with Hilt for dependency injection
 */
@HiltAndroidApp
class MedMobApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Initialize app-level components
    }
}
