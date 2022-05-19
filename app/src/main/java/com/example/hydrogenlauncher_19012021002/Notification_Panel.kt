package com.example.hydrogenlauncher_19012021002

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.SeekBar




class Notification_Panel : AppCompatActivity() {

    //for Brightness Modual
    var seekbar: SeekBar? = null
    var progress: TextView? = null
    var context: Context? = null
    var Brightness = 0
    //Brightness Modual Ends Here



    private lateinit var layout1: LinearLayout

    var txtBatteryStatus: TextView? = null
//    lateinit var progressBar:ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_panel)
        getSupportActionBar()?.hide()



        layout1 = findViewById(R.id.linear1)
        layout1.setOnTouchListener(object : OnSwipeTouchListener(this@Notification_Panel){
            override fun onSwipeUp() {
                super.onSwipeUp()
                Intent(this@Notification_Panel, Launcher_Home::class.java).apply {
                    startActivity(this)
                    overridePendingTransition(R.anim.swipeup_animation,R.anim.swipeup_animation)

                }
            }
        })
        //Gesture Detector Ends Here

        //applying battery modual
        txtBatteryStatus = findViewById(R.id.battery_txt)
        registerReceiver(this.mBatteryInfoReciver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        //Ends Here

    }




    //battry modual
    private val mBatteryInfoReciver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val level = intent!!.getIntExtra(BatteryManager.EXTRA_LEVEL,-1)
            val scale = intent!!.getIntExtra(BatteryManager.EXTRA_SCALE,-1)
            val batterypercantage = level*100 / scale.toFloat()
            txtBatteryStatus!!.text = "Battery status : $batterypercantage%"


            //setting up battery percentage in progrees bar
            val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
            progressBar.max = 100
            progressBar.progress = batterypercantage.toInt()

            val batterytext = findViewById<TextView>(R.id.textbattery)
            batterytext!!.text = "$batterypercantage%"

        }
    }




    //Applying Brightness Modual
    //Getting Current screen brightness.



}