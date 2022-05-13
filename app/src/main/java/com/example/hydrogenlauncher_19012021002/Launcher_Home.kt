package com.example.hydrogenlauncher_19012021002
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toast

class Launcher_Home : AppCompatActivity() {
    private lateinit var layout: RelativeLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher_home)
        getSupportActionBar()?.hide()
//        setStatusBarTransparent()


        //go to right second activity

        val button1 = findViewById<Button>(R.id.gotosecondactivity)
        button1.setOnClickListener {
            Intent(this, secondactivity::class.java).apply {

                startActivity(this)
                overridePendingTransition(R.anim.swiperight_animation, R.anim.swipeleft_animation)
            }
        }

        //goto setting modual

            val setting = findViewById<Button>(R.id.gotosetting)
            setting.setOnClickListener {
                Intent(this, Setting_Modual::class.java).apply {

                    startActivity(this)

                }

            }

            //upto this


            //navigating using button
            val button = findViewById<Button>(R.id.gonext)
            button.setOnClickListener {
                Intent(this, MainActivity::class.java).apply {
                    startActivity(this)
                }


                //infalting dilaogbox
                val button = findViewById<Button>(R.id.dialog_box)
                button.setOnClickListener {
                    val mDialogView =
                        LayoutInflater.from(this).inflate(R.layout.activity_recentdialogbox, null)

                    //alertdialogbuilder
                    val mBuilder = AlertDialog.Builder(this)
                        .setView(mDialogView)
                        .setTitle("recent app use")
                    mBuilder.show()
                }


            }
            layout = findViewById(R.id.relativeLayout)
            layout.setOnTouchListener(object : OnSwipeTouchListener(this@Launcher_Home) {
                override fun onSwipeLeft() {
                    super.onSwipeLeft()
                    Toast.makeText(
                        this@Launcher_Home,
                        "Swipe Left gesture detected",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }

                override fun onSwipeRight() {

                    super.onSwipeRight()
                    Intent(this@Launcher_Home, secondactivity::class.java).apply {
                        startActivity(this)
                        overridePendingTransition(R.anim.swiperight_animation,R.anim.swipeleft_animation)
                    }
                }

                override fun onSwipeUp() {
                    super.onSwipeUp()
                    Intent(this@Launcher_Home, MainActivity::class.java).apply {
                        startActivity(this)
                    }
                }

                override fun onSwipeDown() {
                    super.onSwipeDown()
                    Toast.makeText(
                        this@Launcher_Home,
                        "Swipe down gesture detected",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            })


        }

    }

