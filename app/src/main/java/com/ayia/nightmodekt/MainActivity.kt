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

    private var posTheme : Int? = null
    private var arrayTheme : Array<String>? = null

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

        posTheme =  when (MyApp.instance.appTheme) {
            PREF_MODE_LIGHT -> 0
            PREF_MODE_DARK -> 1
            else -> 2
        }

        btnSwitch.text = arrayTheme!![posTheme!!]


    }

    private fun setTheme(){

        Timber.tag(TAG).d("setTheme")

        btnSwitch.text = arrayTheme!![posTheme!!]

        Timber.tag(TAG).d("Theme $arrayTheme!![posTheme!!]")

        MyApp.instance.setMyAppTheme(posTheme)


    }


    private fun showThemeDialog(){

        Timber.tag(TAG).d("showThemeDialog")


        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.label_select_theme)
            .setSingleChoiceItems(R.array.themes, posTheme!!) { _, i ->

                posTheme = i

                Timber.tag(TAG).d("Theme selected Pos : $posTheme")

                setTheme()

            }.show()

    }


    override fun onStart() {
        super.onStart()
        Timber.tag(TAG).i("onStart")
    }

    override fun onResume() {
        super.onResume()
        Timber.tag(TAG).i("onResume")
    }

    override fun onPause() {
        Timber.tag(TAG).i("onPause")
        super.onPause()
    }

    override fun onStop() {
        Timber.tag(TAG).i("onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Timber.tag(TAG).i("onDestroy")
        super.onDestroy()
    }

    companion object {
        private val TAG: String =
            GLOBAL_TAG + " " + MainActivity::class.java.simpleName
    }
}
