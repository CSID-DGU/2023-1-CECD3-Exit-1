package com.cecd.exitmed.presentation.pillDetail

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.ActivityPillDetailBinding
import com.cecd.exitmed.presentation.pillCreation.PillCreationActivity
import com.cecd.exitmed.util.binding.BindingActivity
import com.cecd.exitmed.util.extension.showToast
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PillDetailActivity :
    BindingActivity<ActivityPillDetailBinding>(R.layout.activity_pill_detail) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLayout()
        addListeners()
    }

    private fun initLayout() {
        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(PillDetailMedicationFragment())
        fragmentList.add(PillDetailComponentsContentFragment())
        fragmentList.add(PillDetailUsageFragment())
        fragmentList.add(PillDetailDURFragment())
        fragmentList.add(PillDetailDrugInteractionFragment())

        val adapter = PillDetailPagerAdapter(fragmentList, this)
        binding.vpPillDetail.adapter = adapter
        setTabTitle()
    }

    private fun addListeners() {
        binding.layoutPillBookmark.setOnClickListener {
            bookMark()
        }
        binding.layoutPillDrawer.setOnClickListener {
            moveToAddPillDrawer()
        }
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun setTabTitle() {
        TabLayoutMediator(binding.tabPillDetail, binding.vpPillDetail) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.pill_detail_medication)
                1 -> tab.text = getString(R.string.pill_detail_components_content)
                2 -> tab.text = getString(R.string.pill_detail_usage)
                3 -> tab.text = getString(R.string.pill_detail_dur)
                4 -> tab.text = getString(R.string.pill_detail_drug_interaction)
            }
        }.attach()
    }

    private fun bookMark() {
        showToast(getString(R.string.pill_detail_bookmark_toast))
    }

    private fun moveToAddPillDrawer() {
        startActivity(Intent(this, PillCreationActivity::class.java))
    }
}
