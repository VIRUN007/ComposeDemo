package com.kosign.webill.wehrmanagement

import android.app.Application
import android.util.Log
import com.kosign.webill.wehrmanagement.utils.localization.LocalHelper.loadLocale
import com.kosign.webill.wehrmanagement.utils.localization.LocalHelper.updateLocale
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}