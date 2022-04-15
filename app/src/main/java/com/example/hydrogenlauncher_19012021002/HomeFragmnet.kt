package com.example.hydrogenlauncher_19012021002

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController


class HomeFragmnet : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_fragmnet, container, false)

        val button = view.findViewById<Button>(R.id.button_home)
        button.setOnClickListener{
            findNavController().navigate(R.id.app_drawer)
        }

        return view
    }


}