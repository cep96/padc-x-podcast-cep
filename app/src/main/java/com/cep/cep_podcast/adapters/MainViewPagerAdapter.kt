package com.cep.cep_podcast.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cep.cep_podcast.fragments.DownloadedFragment
import com.cep.cep_podcast.fragments.HomeFragment
import com.cep.cep_podcast.fragments.ProfileFragment
import com.cep.cep_podcast.fragments.SearchFragment

class MainViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> {
                return HomeFragment.newInstance()
            }
            1 -> {
                return SearchFragment.newInstance()
            }
            2 -> {
                return DownloadedFragment.newInstance()
            }
            3 -> {
                return ProfileFragment.newInstance()
            }
            else -> {
                return HomeFragment.newInstance()
            }
        }
    }
}