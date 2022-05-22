package com.example.hydrogenlauncher_19012021002
import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import android.net.wifi.WifiManager
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.cardview.widget.CardView
import org.w3c.dom.Text
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.getSystemService
import android.widget.TextView

import android.content.ContentResolver
import android.media.VolumeProvider
import android.provider.Settings
import android.provider.Settings.System.SCREEN_BRIGHTNESS
import android.view.Window

import android.widget.SeekBar
import androidx.appcompat.app.AppCompatDelegate
import com.example.hydrogenlauncher_19012021002.databinding.ActivityMainBinding
import java.lang.reflect.Array.getInt

import android.media.AudioManager


class Notification_Panel : AppCompatActivity() {

    //change brightness using seek bar

    var seekbar: SeekBar? = null
    var progress: TextView? = null
    var context: Context? = null
    var Brightness = 0
    var Volume = 0



    //lightdark modual
    lateinit var binding: ActivityMainBinding
    var isNightMode:Boolean=true



    //for Brightness Modual
    private var seekBar: SeekBar? = null

    //Variable to store brightness value
    private var brightness = 0

    //Content resolver used as a handle to the system's settings
    private val cResolver: ContentResolver? = null


    var txtPerc: TextView? = null
    //Brightness Modual Ends Here

    //wifi modual
    lateinit var wifiManager: WifiManager



    //notifcaation bluetooth modual

    lateinit var bAdapter: BluetoothAdapter
    private val REQUEST_CODE_ENABLE_BT:Int = 1




    private lateinit var layout1: RelativeLayout

    var txtBatteryStatus: TextView? = null
//    lateinit var progressBar:ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_panel)
        getSupportActionBar()?.hide()







        //Application of Brightness Modual

       //seekbar = findViewById<SeekBar>(R.id.seek_brightness)
        val progress = findViewById<TextView>(R.id.brightnessvalue)
        val volume_progress = findViewById<ProgressBar>(R.id.Volume_progressbar)
        val txtbrightness = findViewById<TextView>(R.id.txt_brightness)
        val txtVolume = findViewById<TextView>(R.id.txt_Volume)

        context = getApplicationContext()
        Brightness = Settings.System.getInt(contentResolver, SCREEN_BRIGHTNESS,0)
        progress.setText("Screen Brightness : "+ Brightness)
        txtbrightness.setText("Brightness : "+Brightness)

        val brightnessprogress = findViewById<ProgressBar>(R.id.brightness_progressbar)
        brightnessprogress.max = 100
        brightnessprogress.progress = Brightness.toInt()

        //Volume Modual

        Volume = Settings.System.getInt(contentResolver, AUDIO_SERVICE,0)
        txtVolume.setText("Volume : "+ Volume)
        volume_progress.max = 100
        volume_progress.progress = Brightness // Here "Volume" Will rise instead of "Brightness"






//enebaling / desabaling wifi modual

        val card_wifi = findViewById<CardView>(R.id.notification_card_wifi)
        val text_wifi = findViewById<View>(R.id.notification_view_wifi)
        val view_wifi = findViewById<TextView>(R.id.notification_text_wifi)

        card_wifi.setOnClickListener {
           notification_wifi_call()
        }
        text_wifi.setOnClickListener {
            notification_wifi_call()

        }
        view_wifi.setOnClickListener {
            notification_wifi_call()

        }

//enabaling / disabaling lightdark mode

        val card_lightdark = findViewById<CardView>(R.id.notification_card_lightdark)
        val text_lightdark = findViewById<View>(R.id.notification_view_lightdark)
        val view_lightdark = findViewById<TextView>(R.id.notification_text_lightdark)

        card_lightdark.setOnClickListener {
            notification_lightdark_call()
        }
        text_lightdark.setOnClickListener {
            notification_lightdark_call()

        }
        view_lightdark.setOnClickListener {
            notification_lightdark_call()


        }


        //enabaling / disabaling location mode

        val card_location = findViewById<CardView>(R.id.notification_card_location)
        val text_location = findViewById<View>(R.id.notification_view_location)
        val view_location = findViewById<TextView>(R.id.notification_text_location)

        card_location.setOnClickListener {
            notification_location_call()
        }
        text_location.setOnClickListener {
            notification_location_call()

        }
        view_location.setOnClickListener {
            notification_location_call()


        }







        //enebaling / desabaling bluetooth modual

        val card_bluetooth = findViewById<CardView>(R.id.notification_card_bluetooth)
        val text_bluetooth = findViewById<View>(R.id.notification_view_bluetooth)
        val view_bluetooth = findViewById<TextView>(R.id.notification_text_bluetooth)

        card_bluetooth.setOnClickListener {
            notification_bluetooth_call()
        }
        text_bluetooth.setOnClickListener {
            notification_bluetooth_call()
        }
        view_bluetooth.setOnClickListener {
            notification_bluetooth_call()
        }












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
//






        registerReceiver(this.mBatteryInfoReciver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        //Ends Here

    }

    private fun notification_location_call() {
        startActivity(Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS))
    }

    private fun notification_lightdark_call() {
     //Appying lightdark Modual
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (isNightMode)
        {
            isNightMode=false
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            isNightMode=true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        }
    }

    private fun notification_wifi_call() {

        if(wifiManager.isWifiEnabled) {
            val wifi = getApplicationContext().getSystemService(WIFI_SERVICE) as WifiManager
            wifi.isWifiEnabled = false
        }else{
            val wifi = getApplicationContext().getSystemService(WIFI_SERVICE) as WifiManager
            wifi.isWifiEnabled = true
        }

    }

    private fun notification_bluetooth_call() {
       bAdapter = BluetoothAdapter.getDefaultAdapter()
        if (bAdapter.isEnabled){
           Toast.makeText(this,"Already On",Toast.LENGTH_SHORT)
            val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(intent,REQUEST_CODE_ENABLE_BT)
            bAdapter.disable()

        }else{
          //Turn On bluetooth
            val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(intent,REQUEST_CODE_ENABLE_BT)
        }

    }



    //battry modual
    private val mBatteryInfoReciver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val level = intent!!.getIntExtra(BatteryManager.EXTRA_LEVEL,-1)
            val scale = intent!!.getIntExtra(BatteryManager.EXTRA_SCALE,-1)
            val batterypercantage = level*100 / scale.toFloat()
//            txtBatteryStatus!!.text = "Battery status : $batterypercantage%"


            //setting up battery percentage in progrees bar
            val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
            progressBar.max = 100
            progressBar.progress = batterypercantage.toInt()

            val batterytext = findViewById<TextView>(R.id.textbattery)
            batterytext!!.text = "$batterypercantage%"

        }
    }









}