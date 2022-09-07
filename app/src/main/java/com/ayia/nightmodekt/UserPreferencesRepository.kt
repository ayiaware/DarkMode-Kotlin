package com.ayia.nightmodekt

import android.content.Context
import java.util.*


class UserPreferencesRepository (context: Context){

    private val sharedPreferences =
        context.applicationContext.getSharedPreferences(APP_SETTINGS_PREF, Context.MODE_PRIVATE)
    /**
     * Get the appTheme. By default, theme is set to follow system.
     */
    val appTheme: Theme
        get() {
            val theme = sharedPreferences.getString(PREF_NAME_THEME_MODE, Theme.FOLLOW_SYSTEM.name)
            return Theme.valueOf(theme ?: Theme.FOLLOW_SYSTEM.name)
        }

    fun updateTheme(theme: Theme) {
        sharedPreferences.edit()
            .putString(PREF_NAME_THEME_MODE, theme.name)
            .apply()

        ThemeChanger().invoke(theme)
    }


    companion object {
        @Volatile
        private var INSTANCE: UserPreferencesRepository? = null

        fun getInstance(context: Context): UserPreferencesRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE?.let {
                    return it
                }
                val instance = UserPreferencesRepository(context)
                INSTANCE = instance
                instance
            }
        }
    }

}