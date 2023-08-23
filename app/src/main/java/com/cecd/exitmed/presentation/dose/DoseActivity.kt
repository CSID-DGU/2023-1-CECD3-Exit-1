package com.cecd.exitmed.presentation.dose

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.ActivityDoseBinding
import com.cecd.exitmed.util.binding.BindingActivity
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoseActivity : BindingActivity<ActivityDoseBinding>(R.layout.activity_dose) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLayout()
        addListeners()
    }

    private fun initLayout() {
        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(PillDrawerListFragment())
        fragmentList.add(DoseTimeTableFragment())

        val adapter = DosePagerAdapter(fragmentList, this)
        binding.vpDose.adapter = adapter

        TabLayoutMediator(binding.tabDose, binding.vpDose) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.pill_drawer)
                1 -> tab.text = getString(R.string.pill_time_table)
            }
        }.attach()
    }

    private fun addListeners() {
        binding.toolbar.ivBack.setOnClickListener {
            finish()
        }
    }
}
