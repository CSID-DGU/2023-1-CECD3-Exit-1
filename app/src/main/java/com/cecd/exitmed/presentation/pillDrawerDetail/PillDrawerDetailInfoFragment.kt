package com.cecd.exitmed.presentation.pillDrawerDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.FragmentPillDrawerDetailInfoBinding
import com.cecd.exitmed.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PillDrawerDetailInfoFragment :
    BindingFragment<FragmentPillDrawerDetailInfoBinding>(R.layout.fragment_pill_drawer_detail_info) {
    private val viewModel: PillDrawerDetailViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()
    }

    private fun initLayout() {
        val adapter = PillDrawerDetailDoseTimeAdapter()
        binding.rvPillDrawerDetailTime.adapter = adapter
        adapter.setDoseTimeList(viewModel.doseTimeMockList)
    }
}
