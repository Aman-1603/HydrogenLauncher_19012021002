package com.example.hydrogenlauncher_19012021002
import android.animation.Animator
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
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
import android.view.ViewAnimationUtils
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.math.hypot
import kotlin.math.max


class Launcher_Home : AppCompatActivity() {
    private lateinit var layout: RelativeLayout

    //for circularRevelaed Animation

    private var isRevealed = false
    private lateinit var mRevealLayout: View
    private lateinit var mFab: FloatingActionButton




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
                        revealLayoutFun()
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

    private fun revealLayoutFun() {
        if (!isRevealed) {

            // get the right and bottom side
            // lengths of the reveal layout
            val x: Int = mRevealLayout.right
            val y: Int = mRevealLayout.bottom

            // here the starting radius of the reveal
            // layout is 0 when it is not visible
            val startRadius = 0

            // make the end radius should match
            // the while parent view
            val endRadius = hypot(
                mRevealLayout.width.toDouble(),
                mRevealLayout.height.toDouble()
            ).toInt()

            // and set the background tint of the FAB to white
            // color so that it can be visible
            mFab.backgroundTintList = ColorStateList.valueOf(
                ResourcesCompat.getColor(
                    resources,
                    R.color.white,
                    null
                )
            )
            // now set the icon as close for the FAB
            mFab.setImageResource(R.drawable.ic_baseline_close_24)

            // create the instance of the ViewAnimationUtils to
            // initiate the circular reveal animation
            val anim = ViewAnimationUtils.createCircularReveal(
                mRevealLayout,
                x,
                y,
                startRadius.toFloat(),
                endRadius.toFloat()
            )

            // make the invisible reveal layout to visible
            // so that upon revealing it can be visible to user
            mRevealLayout.visibility = View.VISIBLE
            // now start the reveal animation
            anim.start()

            // set the boolean value to true as the reveal
            // layout is visible to the user
            isRevealed = true

        } else {

            // get the right and bottom side lengths
            // of the reveal layout
            val x: Int = mRevealLayout.right
            val y: Int = mRevealLayout.bottom

            // here the starting radius of the reveal layout is its full width
            val startRadius: Int = max(mRevealLayout.width, mRevealLayout.height)

            // and the end radius should be zero
            // at this point because the layout should be closed
            val endRadius = 0

            // now set the background tint of the FAB to green
            // so that it can be visible to the user
            mFab.backgroundTintList = ColorStateList.valueOf(
                ResourcesCompat.getColor(
                    resources,
                    R.color.black,
                    null
                )
            )

            // now again set the icon of the FAB to plus
            mFab.setImageResource(R.drawable.ic_add)

            // create the instance of the ViewAnimationUtils to
            // initiate the circular reveal animation
            val anim = ViewAnimationUtils.createCircularReveal(
                mRevealLayout,
                x,
                y,
                startRadius.toFloat(),
                endRadius.toFloat()
            )

            // now as soon as the animation is ending, the reveal
            // layout should also be closed
            anim.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animator: Animator) {}
                override fun onAnimationEnd(animator: Animator) {
                    mRevealLayout.visibility = View.GONE
                }

                override fun onAnimationCancel(animator: Animator) {}
                override fun onAnimationRepeat(animator: Animator) {}
            })

            // start the closing animation
            anim.start()

            // set the boolean variable to false
            // as the reveal layout is invisible
            isRevealed = false
        }
    }


}

