package com.cecd.exitmed.presentation.pillDetail

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.ActivityPillDetailBinding
import com.cecd.exitmed.presentation.pillCreation.PillCreationActivity
import com.cecd.exitmed.util.binding.BindingActivity
import com.cecd.exitmed.util.binding.setImage
import com.cecd.exitmed.util.extension.showToast
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class PillDetailActivity :
    BindingActivity<ActivityPillDetailBinding>(R.layout.activity_pill_detail) {
    private val pillDetailViewModel: PillDetailViewModel by viewModels()
    private val pillDURViewModel: PillDetailDURViewModel by viewModels()
    var itemSeq: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
        addListeners()
        collectData()
    }

    private fun initLayout() {
        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(PillDetailMedicationFragment())
        fragmentList.add(PillDetailComponentsContentFragment())
        fragmentList.add(PillDetailUsageFragment())
        fragmentList.add(PillDetailCautionFragment())
        fragmentList.add(PillDetailDURFragment())
        fragmentList.add(PillDetailDrugInteractionFragment())

        val adapter = PillDetailPagerAdapter(fragmentList, this)
        binding.vpPillDetail.adapter = adapter
        setTabTitle()

        itemSeq = intent.getIntExtra(ITEM_SEQ, 0)
        fetchPillBasicInfo(itemSeq)
        fetchDurInfo(itemSeq.toString())
    }

    private fun addListeners() {
        binding.layoutPillBookmark.setOnClickListener {
            if (!pillDetailViewModel.isBookMarked.value) {
                bookMarkToast()
                bookmark(itemSeq)
            }
        }
        binding.layoutPillDrawer.setOnClickListener {
            moveToAddPillDrawer()
        }
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun collectData() {
        pillDetailViewModel.pillName.flowWithLifecycle(lifecycle).onEach { pillName ->
            binding.tvPillBasicInfoPillName.text = pillName
        }.launchIn(lifecycleScope)
        pillDetailViewModel.pillClassification.flowWithLifecycle(lifecycle)
            .onEach { pillClassification ->
                binding.tvPillBasicInfoPillClassification.text = pillClassification
            }.launchIn(lifecycleScope)
        pillDetailViewModel.pillImage.flowWithLifecycle(lifecycle)
            .onEach { pillImage ->
                binding.ivPillImage.setImage(pillImage)
            }.launchIn(lifecycleScope)
        pillDetailViewModel.isBookMarked.flowWithLifecycle(lifecycle).onEach { isBookMarked ->
            val bookmarkImage = when (isBookMarked) {
                true -> {
                    R.drawable.ic_heart_bookmark_true
                }

                false -> {
                    R.drawable.ic_heart_bookmark_false
                }
            }
            binding.ivHeartBookmark.setImageResource(bookmarkImage)
        }.launchIn(lifecycleScope)
    }

    private fun setTabTitle() {
        TabLayoutMediator(binding.tabPillDetail, binding.vpPillDetail) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.pill_detail_medication)
                1 -> tab.text = getString(R.string.pill_detail_components_content)
                2 -> tab.text = getString(R.string.pill_detail_usage)
                3 -> tab.text = getString(R.string.pill_detail_caution)
                4 -> tab.text = getString(R.string.pill_detail_dur)
                5 -> tab.text = getString(R.string.pill_detail_drug_interaction)
                6 -> tab.text = getString(R.string.pill_detail_drug_interaction)
            }
        }.attach()
    }

    private fun fetchPillBasicInfo(itemSeq: Int) {
        pillDetailViewModel.fetchPillDetail(itemSeq)
    }

    private fun fetchDurInfo(itemSeq: String) {
        pillDURViewModel.fetchDURAgeProhibitionContents(itemSeq)
        pillDURViewModel.fetchDURPregnantProhibitionContents(itemSeq)
        pillDURViewModel.fetchDURCapacityCautionContents(itemSeq)
        pillDURViewModel.fetchDURAdministrationDurationCaution(itemSeq)
        pillDURViewModel.fetchDURSeniorCautionContents(itemSeq)
    }

    private fun bookmark(itemSeq: Int) {
        pillDetailViewModel.bookmark(itemSeq)
    }

    private fun bookMarkToast() {
        showToast(getString(R.string.pill_detail_bookmark_toast))
    }

    private fun moveToAddPillDrawer() {
        val intent = Intent(this, PillCreationActivity::class.java)
        intent.putExtra(ITEM_SEQ, itemSeq)
        startActivity(intent)
    }

    companion object {
        const val ITEM_SEQ = "itemSeq"
    }
}
