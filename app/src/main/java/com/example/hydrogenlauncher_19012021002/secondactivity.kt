package com.example.hydrogenlauncher_19012021002

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class secondactivity : AppCompatActivity() {
    private lateinit var sp : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondactivity)
        getSupportActionBar()?.hide()

        val recyclerView = findViewById<RecyclerView>(R.id.recycler)
        val arrayList = ArrayList<second_activity_companion>()

        arrayList.add(second_activity_companion("Google Maps", R.drawable.image_map))
//        arrayList.add(ActList("Calculator", R.drawable.calculator))
//        arrayList.add(second_activity_companion("Calender Event", R.drawable.calender))
        arrayList.add(second_activity_companion("Dial Pad", R.drawable.image_dial_pad))
        arrayList.add(second_activity_companion("Contacts", R.drawable.image_contact))
        arrayList.add(second_activity_companion("Browser", R.drawable.image_browser))
        arrayList.add(second_activity_companion("Camera", R.drawable.image_camera))
//        arrayList.add(second_activity_companion("Alarm", R.drawable.alarm))
//        arrayList.add(second_activity_companion("Email", R.drawable.email))
        arrayList.add(second_activity_companion("SMS", R.drawable.image_sms))

        val myAdapter = second_activity_adapter(arrayList,this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = myAdapter


    }
}