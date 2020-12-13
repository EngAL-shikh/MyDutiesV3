package com.amroz.myduties

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.text.FieldPosition
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var  tabLayout: TabLayout
    lateinit var tabViewPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)






        tabLayout=findViewById(R.id.taps)
        tabViewPager=findViewById(R.id.vp) as ViewPager2


        tabViewPager.adapter=object : FragmentStateAdapter(this){
            override fun getItemCount(): Int {
               return 3
            }

            override fun createFragment(position: Int): Fragment {
                return  when(position){

                    0->toDO.newInstance("todo")
                    1->toDO.newInstance("inprogress")
                    2->toDO.newInstance("done")
//                    1->InProgress.newInstance()
//                    2->Done.newInstance()

                    else->toDO.newInstance("todo")
                }
            }


        }

        TabLayoutMediator(tabLayout,tabViewPager){tab,position->
            tab.text=when(position){

                0->"toDO"
                1->"in progress"
                2->"Done"
                else -> null
            }

        }.attach()
    }
}
