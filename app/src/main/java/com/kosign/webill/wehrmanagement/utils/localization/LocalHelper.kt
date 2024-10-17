package com.kosign.webill.wehrmanagement.utils.localization

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import java.util.Locale

object LocalHelper {

    fun Context.updateLocale(locale: Locale) {
        val config = Configuration(resources.configuration)
        Locale.setDefault(locale)
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    fun saveLocale(context: Context, locale: Locale) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("locale", locale.language).apply()
    }

    fun loadLocale(context: Context): Locale {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE)
        val language = sharedPreferences.getString("locale", Locale.getDefault().language) ?: Locale.getDefault().language
        return Locale(language)
    }
}