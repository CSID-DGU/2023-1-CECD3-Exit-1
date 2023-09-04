package com.cecd.exitmed.presentation.pillDetail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PillDetailPagerAdapter(
    private val fragmentList: ArrayList<Fragment>,
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = fragmentList.size
    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}
