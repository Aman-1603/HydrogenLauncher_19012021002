package com.example.hydrogenlauncher_19012021002

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.Image
import android.net.Uri
import android.provider.AlarmClock
import android.provider.CalendarContract
import android.provider.ContactsContract
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import java.security.AccessControlContext
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.ContextCompat.startActivity
import android.content.ComponentName
import android.content.ActivityNotFoundException
import androidx.core.app.ActivityCompat

import androidx.core.content.ContextCompat.startActivity
import androidx.core.app.ActivityCompat.startActivityForResult

class recent_app_adapter(val list : ArrayList<recent_list_companion>, val context: Context):
    RecyclerView.Adapter<recent_app_adapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun binding(lists: recent_list_companion) {
            val names = itemView.findViewById<TextView>(R.id.appTitle)
            val image: ImageView? = itemView.findViewById<ImageView>(R.id.appIcon)

            names.setText(lists.name)
            if (image != null) {
                image.setImageResource(lists.imageID)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val myview =
            LayoutInflater.from(parent.context).inflate(R.layout.recent_app_adapter, parent, false)
        return ViewHolder(myview)
    }

    @SuppressLint("QueryPermissionsNeeded")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(list[position])

        holder.itemView.setOnClickListener {
            if (position == 0) {
                Toast.makeText(context, "Google Maps Opened", Toast.LENGTH_LONG).show()
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("geo:15.3647,75.1240")
                context.startActivity(intent)
            }

        }

        if (position == 1) {
            Toast.makeText(context, "Dial Pad Opened", Toast.LENGTH_LONG).show()
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:+91 8867127270")
            context.startActivity(intent)

        }
        if (position == 2) {
            Toast.makeText(context, "Contacts Opened", Toast.LENGTH_LONG).show()
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = ContactsContract.Contacts.CONTENT_TYPE
            context.startActivity(intent)
        }
        if (position == 3) {
            Toast.makeText(context, "Browser Opened", Toast.LENGTH_LONG).show()
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.google.co.in/")
            context.startActivity(intent)
        }
        if (position == 4) {
            Toast.makeText(context, "Camera Opened", Toast.LENGTH_LONG).show()
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            context.startActivity(intent)
        }



    }


    override fun getItemCount(): Int {
        return list.size
    }

}