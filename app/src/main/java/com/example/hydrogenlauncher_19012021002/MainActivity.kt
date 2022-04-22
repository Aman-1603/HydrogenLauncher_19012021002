package com.example.hydrogenlauncher_19012021002

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import android.content.Intent
import android.content.pm.ResolveInfo
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.hydrogenlauncher_19012021002.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var resolvedApplist: List<ResolveInfo>
    lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)
        resolvedApplist = packageManager
            .queryIntentActivities(Intent(Intent.ACTION_MAIN,null)
                .addCategory(Intent.CATEGORY_LAUNCHER),0)
        val appList = ArrayList<AppBlock>()

        for (ri in resolvedApplist) {
            if(ri.activityInfo.packageName!=this.packageName) {
                val app = AppBlock(
                    ri.loadLabel(packageManager).toString(),
                    ri.activityInfo.loadIcon(packageManager),
                    ri.activityInfo.packageName
                )
                appList.add(app)
            }
        }
        mainBinding.appList.layoutManager = StaggeredGridLayoutManager(4, LinearLayoutManager.VERTICAL )
        mainBinding.appList.adapter = Adapter(this).also {
            it.passAppList(appList.sortedWith(
                Comparator<AppBlock> { o1, o2 -> o1?.appName?.compareTo(o2?.appName?:"",true)?:0; }
            ))
        }
    }




}