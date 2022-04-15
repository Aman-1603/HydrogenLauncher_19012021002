package com.example.hydrogenlauncher_19012021002

import android.app.Activity
import android.content.Intent
import android.content.pm.ResolveInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.hydrogenlauncher_19012021002.databinding.ActivityMainBinding
import com.example.hydrogenlauncher_19012021002.databinding.FragmentAppDrawerBinding

class app_drawer : Fragment(R.layout.fragment_app_drawer) {
    private lateinit var resolvedApplist: List<ResolveInfo>
    lateinit var mainBinding: FragmentAppDrawerBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mainBinding = FragmentAppDrawerBinding.bind(view)
        mainBinding.apply {
           drawerApp.setOnClickListener {

               findNavController().navigate(R.id.HomeFragment)
           }
        }
//        val view = mainBinding.root
//        setContentView(view)
        resolvedApplist = requireActivity().packageManager
            .queryIntentActivities(
                Intent(Intent.ACTION_MAIN,null)
                    .addCategory(Intent.CATEGORY_LAUNCHER),0)
        val appList = ArrayList<AppBlock>()

        for (ri in resolvedApplist) {
            if(ri.activityInfo.packageName!= requireActivity().packageName) {
                val app = AppBlock(
                    ri.loadLabel(requireActivity().packageManager).toString(),
                    ri.activityInfo.loadIcon(requireActivity().packageManager),
                    ri.activityInfo.packageName
                )
                appList.add(app)
            }
        }
        mainBinding.appList.layoutManager = StaggeredGridLayoutManager(4, LinearLayoutManager.VERTICAL )
        mainBinding.appList.adapter = Adapter(requireActivity()).also {
            it.passAppList(appList.sortedWith(
                Comparator<AppBlock> { o1, o2 -> o1?.appName?.compareTo(o2?.appName?:"",true)?:0; }
            ))
        }
        // Inflate the layout for this fragment
//        val view1 = inflater.inflate(R.layout.fragment_app_drawer, container, false)
//        val button1 = findViewById<Button>(R.id.drawer_app)
//        button1.setOnClickListener {
//            findNavController().navigate(R.id.homeFragmnet2)
//        }
//        return view1
    }




}