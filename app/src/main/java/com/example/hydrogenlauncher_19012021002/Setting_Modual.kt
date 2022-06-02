package com.example.hydrogenlauncher_19012021002

import android.app.AlertDialog
import android.appwidget.AppWidgetHost
import android.content.Intent
import android.media.AudioManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

class Setting_Modual : AppCompatActivity() {

    lateinit var imageView: ImageView
    lateinit var button: Button
    private val pickImage = 100
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_modual)
//        setContentView(R.layout.brightness_dialogbox)
        getSupportActionBar()?.hide()






       imageView = findViewById(R.id.profile)
        button = findViewById(R.id.edit_profile)


        //picking image profile
        val profile = findViewById<ImageView>(R.id.profile)
        profile.setOnClickListener{
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }



//navigating through theme

        val theme = findViewById<RelativeLayout>(R.id.theme_relative)
        theme.setOnClickListener {
            Intent(this@Setting_Modual,colorpiker::class.java).apply {
                startActivity(this)
            }
        }




        val button = findViewById<TextView>(R.id.brightness_text)
        button.setOnClickListener {
            val mDialogView =
                LayoutInflater.from(this).inflate(R.layout.brightness_dialogbox, null)

            //alertdialogbuilder
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("Brightness")
            mBuilder.show()
        }

//volume
        val button1 = findViewById<TextView>(R.id.volume_text)
        button1.setOnClickListener {
            val mDialogView =
                LayoutInflater.from(this).inflate(R.layout.volume_dialogbox, null)

            //alertdialogbuilder
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("Volume")
            mBuilder.show()
        }


        val button2 = findViewById<Button>(R.id.edit_profile)

        button2.setOnClickListener {
            val mDialogView =
                LayoutInflater.from(this).inflate(R.layout.edit_profile_dialogbox, null)

            //alertdialogbuilder
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("Volume")
            mBuilder.show()

//          val set_profile = findViewById<Button>(R.id.edit_profile_ok)
//            set_profile.setOnClickListener{
//                //
//            }
        }





//        set Volume Up down Modual


        val volume_up = findViewById<Button>(R.id.volume_up)
        val volume_down = findViewById<Button>(R.id.volume_down)

        // Declare an audio manager
        val audioManager = applicationContext.getSystemService(AUDIO_SERVICE) as AudioManager

        // At the click of upBtn
        volume_up.setOnClickListener {
            // ADJUST_RAISE = Raise the volume, FLAG_PLAY_SOUND = make a sound when clicked
            audioManager.adjustVolume(AudioManager.ADJUST_RAISE,AudioManager.FLAG_SHOW_UI)
        }
        // At the click of downBtn
        volume_down.setOnClickListener {
            // ADJUST_LOWER = Lowers the volume, FLAG_PLAY_SOUND = make a sound when clicked
            audioManager.adjustVolume(AudioManager.ADJUST_LOWER,AudioManager.FLAG_SHOW_UI)
        }


    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            imageView.setImageURI(imageUri)
        }
    }


}


