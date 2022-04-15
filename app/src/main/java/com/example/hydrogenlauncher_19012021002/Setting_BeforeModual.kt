package com.example.hydrogenlauncher_19012021002

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton


class Setting_BeforeModual : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view2 =  inflater.inflate(R.layout.fragment_setting__before_modual, container, false)
        val button = view2.findViewById<FloatingActionButton>(R.id.fab_setting)
        button.setOnClickListener{
            findNavController().navigate(R.id.action_setting_BeforeModual_to_settingsFragment)
        }

        return view2
    }
}