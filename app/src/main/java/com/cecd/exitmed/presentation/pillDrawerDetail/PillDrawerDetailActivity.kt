package com.cecd.exitmed.presentation.pillDrawerDetail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.ActivityPillDrawerDetailBinding
import com.cecd.exitmed.util.binding.BindingActivity
import com.cecd.exitmed.util.binding.setImage
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class PillDrawerDetailActivity :
    BindingActivity<ActivityPillDrawerDetailBinding>(R.layout.activity_pill_drawer_detail) {
    private val viewModel: PillDrawerDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLayout()
        addListeners()
        collectData()
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

        fetchDrawerPillDetail()
    }

    private fun fetchDrawerPillDetail() {
        val itemSeq = intent.getIntExtra(ITEM_SEQ, -1)
        viewModel.fetchDrawerPillDetail(itemSeq)
    }

    private fun addListeners() {
        binding.toolbar.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun collectData() {
        viewModel.drawerPillDetail.flowWithLifecycle(lifecycle).onEach { drawerPillDetail ->
            drawerPillDetail?.imageLink?.let { binding.ivPillDrawerDetail.setImage(it) }
            binding.toolbar.title = drawerPillDetail?.pillName
        }.launchIn(lifecycleScope)
    }

    companion object {
        const val ITEM_SEQ = "itemSeq"
    }
}
