package com.cecd.exitmed.presentation.pillDrawerDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.FragmentPillDrawerDetailInfoBinding
import com.cecd.exitmed.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class PillDrawerDetailInfoFragment :
    BindingFragment<FragmentPillDrawerDetailInfoBinding>(R.layout.fragment_pill_drawer_detail_info) {
    private val viewModel: PillDrawerDetailViewModel by activityViewModels()
    lateinit var adapter: PillDrawerDetailDoseTimeAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()
        collectData()
    }

    private fun initLayout() {
        adapter = PillDrawerDetailDoseTimeAdapter()
        binding.rvPillDrawerDetailTime.adapter = adapter
    }

    private fun collectData() {
        viewModel.drawerPillDetail.flowWithLifecycle(lifecycle).onEach { drawerPillDetail ->
            binding.tvPillDrawerDetailLastDoseContent.text = drawerPillDetail?.finalDoseDate
            if (drawerPillDetail != null) {
                adapter.setDoseTimeList(drawerPillDetail.doseTime)
            }
        }.launchIn(lifecycleScope)
    }
}
