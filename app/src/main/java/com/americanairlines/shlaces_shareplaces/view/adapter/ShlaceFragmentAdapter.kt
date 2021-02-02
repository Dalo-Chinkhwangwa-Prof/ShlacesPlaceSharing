package com.americanairlines.shlaces_shareplaces.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.americanairlines.shlaces_shareplaces.view.ui.fragment.MyProfileFragment
import com.americanairlines.shlaces_shareplaces.view.ui.fragment.ShlacesFragment

class ShlaceFragmentAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(
    fragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    private val shlacesFragment = ShlacesFragment()
    private val myProfileFragment = MyProfileFragment()

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> shlacesFragment
            else -> myProfileFragment
        }
    }

    override fun getCount(): Int = 2
}