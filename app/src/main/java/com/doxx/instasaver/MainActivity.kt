package com.doxx.instasaver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabs = findViewById<TabLayout>(R.id.tab)
        val viewPager=findViewById<ViewPager>(R.id.container)
        val adaptor = ViewPagerAdaptor(supportFragmentManager)

        adaptor.addFrag(ReelsFragment(),"Reels")
        adaptor.addFrag(IgtvFragment(),"dad")
        adaptor.addFrag(VideoFragment(),"video")

        viewPager.adapter=adaptor
        tabs.setupWithViewPager(viewPager)


    }
}