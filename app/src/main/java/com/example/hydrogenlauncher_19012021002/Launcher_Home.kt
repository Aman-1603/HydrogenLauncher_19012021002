package com.example.hydrogenlauncher_19012021002
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.transition.Transition
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.*

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
                    Intent(this@Launcher_Home, secondactivity::class.java).apply {
                        startActivity(this)
                        overridePendingTransition(R.anim.swiperight_animation,R.anim.swipeleft_animation)
                    }
                }

//                override fun onSwipeRight() {
//
//                    super.onSwipeRight()
//                    Intent(this@Launcher_Home, secondactivity::class.java).apply {
//                        startActivity(this)
//                        overridePendingTransition(R.anim.swiperight_animation,R.anim.swipeleft_animation)
//                    }
//                }

                override fun onSwipeUp() {
                    super.onSwipeUp()
                    Intent(this@Launcher_Home, MainActivity::class.java).apply {
                        startActivity(this)
                        overridePendingTransition(R.anim.swipeup_animation,R.anim.swipedown_animation)

                    }
                }

                override fun onSwipeDown() {
                    super.onSwipeDown()
                    Intent(this@Launcher_Home, Notification_Panel::class.java).apply {
                        startActivity(this)
                        overridePendingTransition(R.anim.swipedown_animation,R.anim.swipedown_animation)

                    }
                }

                override fun onLongClick() {
                    super.onLongClick()
                    val mDialogView =
                        LayoutInflater.from(this@Launcher_Home).inflate(R.layout.activity_home_screen_long_press, null)

                    //alertdialogbuilder
                    val mBuilder = AlertDialog.Builder(this@Launcher_Home)
                        .setView(mDialogView)
                        .setTitle("Quick Panel")
                    mBuilder.show()
                        overridePendingTransition(R.anim.swipedown_animation,R.anim.swipedown_animation)

                    }


            })



        //navigatiging between application

        val dial = findViewById<View>(R.id.nav_dial)
        dial.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:+91 8867127270")
            startActivity(intent)
        }

        val contact = findViewById<View>(R.id.nav_contact)
        contact.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = ContactsContract.Contacts.CONTENT_TYPE
            startActivity(intent)
        }

        val sms = findViewById<View>(R.id.nav_sms)
        sms.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)


            intent.type = "vnd.android-dir/mms-sms"

            startActivity(intent)
        }

        val camera = findViewById<View>(R.id.nav_camera)
        camera.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
        }

        val gallery = findViewById<View>(R.id.nav_browser)
        gallery.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.google.co.in/")
            startActivity(intent)
        }


        }

    }

