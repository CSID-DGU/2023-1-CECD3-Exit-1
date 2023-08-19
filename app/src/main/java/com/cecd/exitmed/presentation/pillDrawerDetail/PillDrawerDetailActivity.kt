package com.cecd.exitmed.presentation.pillDrawerDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.ActivityPillDrawerDetailBinding
import com.cecd.exitmed.util.binding.BindingActivity
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PillDrawerDetailActivity :
    BindingActivity<ActivityPillDrawerDetailBinding>(R.layout.activity_pill_drawer_detail) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLayout()
        addListeners()
    }

    private fun initLayout() {
        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(PillDrawerDetailInfoFragment())
        fragmentList.add(PillDrawerDetailPillInfoFragment())
        fragmentList.add(PillDrawerDetailMemoFragment())

        val adapter = PillDrawerDetailPagerAdapter(fragmentList, this)
        binding.vpPillDrawerDetail.adapter = adapter

        TabLayoutMediator(
            binding.tabPillDrawerDetail,
            binding.vpPillDrawerDetail
        ) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.pill_drawer_detail_info_title)
                1 -> tab.text = getString(R.string.pill_drawer_detail_pill_info_title)
                2 -> tab.text = getString(R.string.pill_drawer_detail_memo_title)
            }
        }.attach()
    }

    private fun addListeners() {
        binding.toolbar.ivBack.setOnClickListener {
            finish()
        }
    }
}