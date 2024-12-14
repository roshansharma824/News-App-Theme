package com.example.newsapptheme

import android.app.Application
import com.roshan.themebuilder.ui.ThemeViewModel
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ConsCentNewsApp : Application() {
    // Singleton ViewModel instance
    lateinit var themeViewModel: ThemeViewModel
        private set

    override fun onCreate() {
        super.onCreate()
        // Initialize ViewModel once for the entire app lifecycle
        themeViewModel = ThemeViewModel(this)
    }
}