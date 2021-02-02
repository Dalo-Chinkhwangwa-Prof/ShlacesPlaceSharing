package com.americanairlines.shlaces_shareplaces.view.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.americanairlines.shlaces_shareplaces.R
import com.americanairlines.shlaces_shareplaces.view.adapter.ShlaceFragmentAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    private lateinit var navigationView: BottomNavigationView
    private lateinit var profileViewPager: ViewPager
    private lateinit var shlaceFragmentAdapter: ShlaceFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        shlaceFragmentAdapter = ShlaceFragmentAdapter(supportFragmentManager)


        profileViewPager = findViewById(R.id.profile_viewpager)

        profileViewPager.adapter = shlaceFragmentAdapter

        profileViewPager.addOnPageChangeListener(this)

        navigationView = findViewById(R.id.profile_bottom_nav)

        navigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.places_item -> loadFragment(0)
                else -> loadFragment(1)
            }
            true
        }
    }

    private fun loadFragment(fragmentId: Int) {
        profileViewPager.currentItem = fragmentId
    }

    override fun onPageScrollStateChanged(state: Int) {
        //Do nothing
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        //Do nothing
    }

    override fun onPageSelected(position: Int) {

        navigationView.selectedItemId = when (position) {
            0 -> R.id.places_item
            else -> R.id.person_item
        }
    }
}