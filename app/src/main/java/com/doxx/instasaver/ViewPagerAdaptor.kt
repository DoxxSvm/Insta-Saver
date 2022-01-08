package com.doxx.instasaver

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import java.util.ArrayList;
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdaptor(fragmentManager: FragmentManager):
    FragmentPagerAdapter(
    fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
//    override fun getItemCount(): Int {
//        return 5;
//    }
//
//    override fun createFragment(position: Int): Fragment {
//        return when(position){
//            1->{
//                ReelsFragment()
//            }
//            2->{
//                IgtvFragment()
//            }
//            3->{
//                VideoFragment()
//            }
//            4->{
//                PostsFragment()
//            }
//            5->{
//                DpFragment()
//            }
//            else ->{
//                Fragment()
//            }
//        }
//    }
    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()




    override fun getCount(): Int {
        return mFragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }

    fun addFrag(fragment: Fragment,title:String){
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }
}