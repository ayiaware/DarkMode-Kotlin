package com.ayia.nightmodekt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import timber.log.Timber

/**
 * Created by ayia on 23/04/2020.
 */


class MainActivity : AppCompatActivity() {

    private var themePosition : Int? = null
    private var arrayTheme : Array<String>? = null

    private var userPrefs : UserPreferencesRepository = MyApp.instance.userPreferences

    private lateinit var btnSwitch: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSwitch = findViewById(R.id.btnSwitch)


        initTheme()


        btnSwitch.setOnClickListener {

            showThemeDialog()

        }


    }


    private fun initTheme(){

        Timber.tag(TAG).d("initTheme")

        arrayTheme = resources.getStringArray(R.array.themes)

        themePosition = when (userPrefs.appTheme) {
            Theme.LIGHT_MODE -> 0
            Theme.DARK_MODE -> 1
            else -> 2
        }

        btnSwitch.text = arrayTheme!![themePosition!!]

    }

    private fun setTheme(){

        Timber.tag(TAG).d("setTheme")

        btnSwitch.text = arrayTheme!![themePosition!!]

        Timber.tag(TAG).d("Theme $arrayTheme!![posTheme!!]")

        userPrefs.updateTheme(
            when (themePosition) {
                0 -> Theme.LIGHT_MODE
                1 -> Theme.DARK_MODE
                else -> Theme.FOLLOW_SYSTEM
            }
        )

    }


    private fun showThemeDialog(){

        Timber.tag(TAG).d("showThemeDialog")

        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.label_select_theme)
            .setSingleChoiceItems(R.array.themes, themePosition!!) { _, i ->

                themePosition = i

                Timber.tag(TAG).d("Theme selected Pos : $themePosition")

                setTheme()

            }.show()

    }

    companion object {
        private val TAG: String =
            GLOBAL_TAG + " " + MainActivity::class.java.simpleName
    }
}
