package com.example.hydrogenlauncher_19012021002

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class HomeScreen_LongPress : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen_long_press)

        val setting = findViewById<View>(R.id.home_dialog_setting)
        setting.setOnClickListener {
            Intent(this, Setting_Modual::class.java).apply {

                startActivity(this)
            }
        }


        val wallpaper = findViewById<View>(R.id.home_dialog_wallpaper)
        wallpaper.setOnClickListener {
            Intent(this, Wallpaper_Modual::class.java).apply {

                startActivity(this)
            }
        }


    }
}