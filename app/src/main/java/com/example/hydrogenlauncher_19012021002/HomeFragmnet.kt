package com.example.hydrogenlauncher_19012021002

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import java.util.*


class HomeFragmnet : Fragment() {

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_fragmnet, container, false)

        val button = view.findViewById<Button>(R.id.button_home)
        button.setOnClickListener{
            findNavController().navigate(R.id.action_HomeFragment_to_app_drawer)
        }

        val button1 = view.findViewById<Button>(R.id.goto_setting)
        button1.setOnClickListener{
            findNavController().navigate(R.id.action_HomeFragment_to_setting_BeforeModual)
        }

        //Navigating using gesture controll










        return view


        //






    }


}